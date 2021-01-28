package net.brekitomasson.coloredslime.util;

import net.brekitomasson.coloredslime.ColoredSlime;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Logger {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private final String mod = "[" + ColoredSlime.MOD_ID + "] ";

    private Logger() {}

    public static Logger get() {
        return new Logger();
    }

    public void log(Level level, String message) {
        LOGGER.log(level, mod + message);
    }

    public void log(Level level, String message, Object... params) {
        LOGGER.log(level, mod + message, params);
    }

    public void debug(Object message) {
        this.log(Level.DEBUG, message.toString());
    }

    public void debug(Object message, Object... params) {
        this.log(Level.DEBUG, message.toString(), params);
    }

    public void catching(Throwable ex) {
        this.error(ex.getLocalizedMessage());
        LOGGER.catching(ex);
    }

    public void info(String message) {
        this.log(Level.INFO, message);
    }

    public void info(String message, Object... params) {
        this.log(Level.INFO, message, params);
    }

    public void warning(String message, Object... params) {
        this.log(Level.WARN, message, params);
    }

    public void warning(String message, Object obj, Exception ex) {
        LOGGER.warn(mod + message, obj, ex);
    }

    public void error(String message) {
        this.log(Level.ERROR, message);
    }

    public void error(String message, Object obj, Exception ex) {
        LOGGER.error(mod + message, obj, ex);
    }

    public void error(String message, Exception ex) {
        LOGGER.error(mod + message, ex);
    }


}
