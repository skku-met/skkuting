package skkumet.skkuting.util.errorcode;

public interface ErrorCode {
    public Integer getHttpStatus();

    public String getCode();

    public String getDescription();
}
