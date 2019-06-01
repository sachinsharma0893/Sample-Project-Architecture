package com.sample.samplePoc.ExceptionUtils;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 9036656826324885899L;

	protected String message;
	protected Object[] args;
	protected ResponseMessage.Code code;

	public ValidationException() {
	}

	public ValidationException(ResponseMessage.Code code, String message) {
		super(message, null, false, false);
		this.code = code;
		this.message = message;
	}

	ValidationException(ResponseMessage.Code code, String message, Throwable t) {
		super(message, t, false, false);
	}

	public ValidationException(ResponseMessage.Code code, String message, Object[] args) {
		super(message, null, false, false);
		this.code = code;
		this.message = message;
		this.args = args;
	}

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}

	/**
	 * @return the code
	 */
	public ResponseMessage.Code getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(ResponseMessage.Code code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
