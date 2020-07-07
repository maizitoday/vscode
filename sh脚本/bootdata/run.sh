PROG=bootdata-1.1.jar
PIDFILE=bootdata.pid
#JAVA_HOME=/data/jdk1.8.0_45
status() {
    if [ -f $PIDFILE ]; then
        PID=$(cat $PIDFILE)
        if [ ! -x /proc/${PID} ]; then
            return 1
        else
            return 0
        fi
    else
        return 1
    fi
}

case "$1" in
    start)
    	status
    	RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
			echo "$PIDFILE exists, process is already running or crashed"
			exit 1
		fi
		
        echo "Starting $PROG ..."
        nohup $JAVA_HOME/bin/java -server -Xms206m -Xmx512m -DappName=bootdatatest -Xdebug -Xrunjdwp:transport=dt_socket,address=38783,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom -jar $PROG > bootdata.log 2>&1 &
		RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
			echo "$PROG is started"
			echo $! > $PIDFILE
			exit 0
		else
			echo "Stopping $PROG"
			rm -f $PIDFILE
			exit 1
		fi
        ;;
    stop)
    	status
    	RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
			echo "Shutting down $PROG"
			kill `cat $PIDFILE`
			RETVAL=$?
			if [ $RETVAL -eq 0 ]; then
				cp $PROG $PROG.bak
				rm -f $PIDFILE
			else
				echo "Failed to stopping $PROG"
			fi
		fi
        ;;
    status)
    	status
    	RETVAL=$?
		if [ $RETVAL -eq 0 ]; then
	    	PID=$(cat $PIDFILE)
			echo "$PROG is running ($PID)"
		else
			echo "$PROG is not running"
		fi
        ;;
    restart)
        $0 stop
        $0 start
        ;;
    *)
		echo "Usage: $0 {start|stop|restart|status}"
		;;
esac


