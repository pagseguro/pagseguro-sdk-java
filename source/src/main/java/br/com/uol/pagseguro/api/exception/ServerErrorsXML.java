package br.com.uol.pagseguro.api.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "errors")
public class ServerErrorsXML implements ServerErrors {

  private List<ServerErrorXML> errors;

  ServerErrorsXML() {
  }

  @Override
  public List<ServerErrorXML> getErrors() {
    return errors;
  }

  @XmlElement(name = "error")
  public void setErrors(List<ServerErrorXML> errors) {
    this.errors = errors;
  }

  @Override
  public int size() {
    return errors.size();
  }

  @Override
  public ServerError getError(int code) {
    for (ServerErrorXML error : errors) {
      if (error.getCode() == code) {
        return error;
      }
    }
    return null;
  }

  @Override
  public boolean contains(int code) {
    return getError(code) != null;
  }

  @Override
  public String toString() {
    return "ServerErrorsXML [errors=" + errors + "]";
  }

  public static class ServerErrorXML implements ServerError {

    private Integer code;

    private String message;

    ServerErrorXML() {
    }

    @Override
    public Integer getCode() {
      return code;
    }

    @XmlElement(name = "code")
    public void setCode(Integer code) {
      this.code = code;
    }

    @Override
    public String getMessage() {
      return message;
    }

    @XmlElement(name = "message")
    public void setMessage(String message) {
      this.message = message;
    }

    @Override
    public String toString() {
      return "ServerErrorXML [code=" + code + ", message=" + message + "]";
    }

  }

}
