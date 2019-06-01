package com.sample.samplePoc.ExceptionUtils;

public class ResponseMessage {

	public static enum Code {

		INTERNAL_ERROR_GENERIC(MessageType.ERROR), 
		// Job Related Messages/Errors
		
		INVALID_JOB_CATEGORY_FOUND(
				MessageType.ERROR),
		INVALID_JOB_ID_FOUND(
				MessageType.ERROR),JOB_TITLE_REQUIRED(MessageType.ERROR),JOB_TITLE_LIMIT_EXCEED(MessageType.ERROR), JOB_UPDATED_SUCCESSFULLY(
						MessageType.SUCCESS), JOB_DELETED_SUCCESSFULLY(MessageType.SUCCESS);

		public static enum MessageType {
			ERROR, SUCCESS
		}

		protected MessageType type;

		private Code(MessageType type) {
			this.type = type;
		}

		/**
		 * @return the type
		 */
		public MessageType getType() {
			return type;
		}

	}

	private String message;

	private Code code;

	public ResponseMessage() {
	}

	public ResponseMessage(ResponseMessage.Code code, String message) {

		this.message = message;
		this.code = code;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

}
