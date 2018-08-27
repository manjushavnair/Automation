package com.automation.ui.base.common.exception;

import com.automation.ui.base.common.prpreaders.ExceptionErrorCodeReader;
import org.apache.log4j.Logger;

public class BusinessException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(BusinessException.class);
    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(String auxMsg) {
        super(auxMsg);
        this.msg = auxMsg;

    }

    public BusinessException(String errorCode, String auxMsg) {
        super(auxMsg);
        this.msg = makeMessage(errorCode, auxMsg);
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
