package com.jitlogic.zorka.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ConsoleTrapper extends ZorkaAsyncThread<String> implements ZorkaTrapper{
	private static final Logger log = Logger.getLogger(ConsoleTrapper.class.getName());

	public ConsoleTrapper(String name) {
	    super(name);
    }

	@Override
	public void trap(ZorkaLogLevel logLevel, String tag, String message, Throwable e, Object... args) {
		StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sb.append(" ");
        sb.append(logLevel);
        sb.append(" ");
        sb.append(tag);
        sb.append(" ");
        sb.append(format(message, args));

        log.info(sb.toString());
    }

	@Override
    protected void process(List<String> obj) {
	    // TODO Auto-generated method stub
	    
    }

	 /**
     * Formats string. Used by other trapper functions. If there are no arguments, string formatting
     * is skipped and message string (template) is returned right away. See String.format() description
     * for more information about string formatting.
     *
     * @param message message string (template)
     * @param args    arguments for String.format() function
     * @return formatted string
     */
    public String format(String message, Object... args) {
        if (args.length == 0) {
            return message;
        } else {
            try {
                return String.format(message, args);
            } catch (Exception e) {
                return "Invalid format '" + message + "' [" + ZorkaUtil.join(",", args) + "]: " + e;
            }
        }
    }

}
