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

