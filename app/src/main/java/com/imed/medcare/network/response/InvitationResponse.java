package com.imed.medcare.network.response;

import java.util.List;

public class InvitationResponse {


    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : patients
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [{"id":9,"membership_id":2,"user_id":24,"invitation_cuid":"18236665-k","invitation_name":"ismael alvarez","type":1,"status":2,"created_at":"2018-10-30 15:12:10","updated_at":"2018-11-04 18:21:46"}]
     */

    private String status;
    private String message;
    private String resource;
    private LinksBean links;
    private Object page;
    private int total;
    private Object per_page;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getPer_page() {
        return per_page;
    }

    public void setPer_page(Object per_page) {
        this.per_page = per_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class LinksBean {
    }

    public static class DataBean {
        /**
         * id : 9
         * membership_id : 2
         * user_id : 24
         * invitation_cuid : 18236665-k
         * invitation_name : ismael alvarez
         * type : 1
         * status : 2
         * created_at : 2018-10-30 15:12:10
         * updated_at : 2018-11-04 18:21:46
         */

        private int id;
        private int membership_id;
        private int user_id;
        private String invitation_cuid;
        private String invitation_name;
        private int type;
        private int status;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMembership_id() {
            return membership_id;
        }

        public void setMembership_id(int membership_id) {
            this.membership_id = membership_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getInvitation_cuid() {
            return invitation_cuid;
        }

        public void setInvitation_cuid(String invitation_cuid) {
            this.invitation_cuid = invitation_cuid;
        }

        public String getInvitation_name() {
            return invitation_name;
        }

        public void setInvitation_name(String invitation_name) {
            this.invitation_name = invitation_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
