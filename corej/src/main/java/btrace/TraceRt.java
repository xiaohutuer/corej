package btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.TLS;

@BTrace
public class TraceRt {

	@TLS
	private static long startTime = 0;

	@OnMethod(clazz = "com.weibo.api.statuses.resources.Statuses", method = "show")
	public static void startExecute() {
		startTime = BTraceUtils.timeMillis();
	}

	@OnMethod(clazz = "com.weibo.api.statuses.resources.Statuses", method = "show", location = @Location(Kind.RETURN))
	public static void endExecute(@Duration long duration) {
		long time = BTraceUtils.timeMillis() - startTime;
		BTraceUtils.println("######curtime=" + time);

	}

}
