package com.automation.ui.connected.common.exception;

import com.automation.ui.base.common.prpreaders.ExceptionErrorCodeReader;
import org.apache.log4j.Logger;

public class UIException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(UIException.class);
    private String msg;

    public UIException() {
        super();
        logger.info("Default constructor");
    }

    public UIException(String auxMsg) {
        super(auxMsg);
        this.msg = auxMsg;
        logger.info("Second constructor");

    }

    public UIException(String errorCode, String auxMsg) {
        super(auxMsg);
        this.msg = makeMessage(errorCode, auxMsg);
        logger.info("Third constructor");
    }

    private String makeMessage(String errorCode, String auxMsg) {

        ExceptionErrorCodeReader prpr = ExceptionErrorCodeReader.readProperty();

        String errorMessage = prpr.getProperty(errorCode);

        return errorCode + " : Custom Message" + errorMessage
                + " : Aux Message" + auxMsg;

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {

        return this.msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }

}
