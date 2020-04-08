package com.imed.medcare.network.response;

import java.util.List;

/**
 * Created by Ramiro on 03-04-2018.
 */

public class LoginResponse extends BaseResponse{


    /**
     * message : ok
     * resource : user
     * data : [{"token_type":"bearer","access_token":"64ebde8a-5e4e-48d3-be60-5d9b26f2ae6c","refresh_token":"fcdc7b17-e4fe-454d-add0-0b5789fdfb0b","expires_in":1999999,"scope":"openid profile"}]
     */

    private String message;
    private String resource;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token_type : bearer
         * access_token : 64ebde8a-5e4e-48d3-be60-5d9b26f2ae6c
         * refresh_token : fcdc7b17-e4fe-454d-add0-0b5789fdfb0b
         * expires_in : 1999999
         * scope : openid profile
         */

        private String token_type;
        private String access_token;
        private String refresh_token;
        private int expires_in;
        private String scope;

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }
}
