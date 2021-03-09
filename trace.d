#pragma D option quiet
#pragma D option destructive


BEGIN
{
    /* -1: trace this process; 0: do not trace process; */
    trackedpid[pid] = 0; 
    trackedpid[$target] = -1;
    printf("Dtrace started for PID %d.\n", $target);
}

syscall::thread_selfid:entry
/(trackedpid[ppid] == -1) && (trackedpid[pid] == 0)/
{
	trackedpid[pid] = -1;
}

syscall:::entry
/trackedpid[pid] == -1/
{
    @num[probefunc] = count();
}

tcp:::send
/trackedpid[pid] == -1 && connections[pid,args[1]->ip_daddr,args[1]->dport] == 0/
{
    connections[pid, args[1]->ip_daddr, args[1]->dport] = -1;
    printf(
            "Packet sent:  %s:%u -> %s:%u on behalf of %s (PID: %d, UID: %d)\n",
            args[1]->ip_saddr, args[1]->sport, args[1]->ip_daddr,
            args[1]->dport, execname, pid, uid);
}

syscall::exit:entry
/trackedpid[pid] == -1/
{
    printf("Deactivate tracing for PID %d\n", pid);
    trackedpid[pid] = 0;
}

syscall::exit:entry
/pid == $target/
{
	printf("Root PID %d stopped, exiting...", pid);
	exit(0);
}

