package top.testeru.entity;

public class TokenResponse extends BaseResponse{
    private String accessToken;

    @Override
    public String toString() {
        return "GetTokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
