package com.imed.medcare.network.response;

public class PersonalProfileSetAnswerResponse {

    /**
     * status : success
     * message : ok
     * resource : record
     * code : 200
     * links : {}
     * data : {"id":20,"user_id":23,"gender_id":1,"race_id":5,"nationality_id":1,"birth_country_id":null,"prevision_id":1,"civil_state_id":1,"cohabitant_id":"3","study_level_id":1,"occupational_situation_id":1,"addresses":null,"birthdate":"2019-01-09","created_at":"2019-01-14 19:31:17","updated_at":"2019-01-17 12:08:19","emergency_contact_name":null,"emergency_contact_phone":null}
     */

    private String status;
    private String message;
    private String resource;
    private int code;
    private LinksBean links;
    private DataBean data;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class LinksBean {
    }

    public static class DataBean {
        /**
         * id : 20
         * user_id : 23
         * gender_id : 1
         * race_id : 5
         * nationality_id : 1
         * birth_country_id : null
         * prevision_id : 1
         * civil_state_id : 1
         * cohabitant_id : 3
         * study_level_id : 1
         * occupational_situation_id : 1
         * addresses : null
         * birthdate : 2019-01-09
         * created_at : 2019-01-14 19:31:17
         * updated_at : 2019-01-17 12:08:19
         * emergency_contact_name : null
         * emergency_contact_phone : null
         */

        private int id;
        private int user_id;
        private int gender_id;
        private int race_id;
        private int nationality_id;
        private Object birth_country_id;
        private int prevision_id;
        private int civil_state_id;
        private String cohabitant_id;
        private int study_level_id;
        private int occupational_situation_id;
        private Object addresses;
        private String birthdate;
        private String created_at;
        private String updated_at;
        private Object emergency_contact_name;
        private Object emergency_contact_phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getGender_id() {
            return gender_id;
        }

        public void setGender_id(int gender_id) {
            this.gender_id = gender_id;
        }

        public int getRace_id() {
            return race_id;
        }

        public void setRace_id(int race_id) {
            this.race_id = race_id;
        }

        public int getNationality_id() {
            return nationality_id;
        }

        public void setNationality_id(int nationality_id) {
            this.nationality_id = nationality_id;
        }

        public Object getBirth_country_id() {
            return birth_country_id;
        }

        public void setBirth_country_id(Object birth_country_id) {
            this.birth_country_id = birth_country_id;
        }

        public int getPrevision_id() {
            return prevision_id;
        }

        public void setPrevision_id(int prevision_id) {
            this.prevision_id = prevision_id;
        }

        public int getCivil_state_id() {
            return civil_state_id;
        }

        public void setCivil_state_id(int civil_state_id) {
            this.civil_state_id = civil_state_id;
        }

        public String getCohabitant_id() {
            return cohabitant_id;
        }

        public void setCohabitant_id(String cohabitant_id) {
            this.cohabitant_id = cohabitant_id;
        }

        public int getStudy_level_id() {
            return study_level_id;
        }

        public void setStudy_level_id(int study_level_id) {
            this.study_level_id = study_level_id;
        }

        public int getOccupational_situation_id() {
            return occupational_situation_id;
        }

        public void setOccupational_situation_id(int occupational_situation_id) {
            this.occupational_situation_id = occupational_situation_id;
        }

        public Object getAddresses() {
            return addresses;
        }

        public void setAddresses(Object addresses) {
            this.addresses = addresses;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
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

        public Object getEmergency_contact_name() {
            return emergency_contact_name;
        }

        public void setEmergency_contact_name(Object emergency_contact_name) {
            this.emergency_contact_name = emergency_contact_name;
        }

        public Object getEmergency_contact_phone() {
            return emergency_contact_phone;
        }

        public void setEmergency_contact_phone(Object emergency_contact_phone) {
            this.emergency_contact_phone = emergency_contact_phone;
        }
    }
}
