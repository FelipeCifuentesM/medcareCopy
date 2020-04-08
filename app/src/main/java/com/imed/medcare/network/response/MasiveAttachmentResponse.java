package com.imed.medcare.network.response;

import java.util.List;

public class MasiveAttachmentResponse {

    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : attachments
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [[{"prescription_id":5,"date":"2019-01-13","time":"17:40:19","dose":"0","response_date":"2019-01-13","response_time":"20:00:00","response_dose":"0","status":1,"updated_at":"2019-01-13 17:40:21","created_at":"2019-01-13 17:40:21","id":29}]]
     */

    private String status;
    private String message;
    private String resource;
    private LinksBean links;
    private Object page;
    private int total;
    private Object per_page;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class LinksBean {
    }

    public static class DataBean {
        /**
         * prescription_id : 5
         * date : 2019-01-13
         * time : 17:40:19
         * dose : 0
         * response_date : 2019-01-13
         * response_time : 20:00:00
         * response_dose : 0
         * status : 1
         * updated_at : 2019-01-13 17:40:21
         * created_at : 2019-01-13 17:40:21
         * id : 29
         */

        private int prescription_id;
        private String date;
        private String time;
        private String dose;
        private String response_date;
        private String response_time;
        private String response_dose;
        private int status;
        private String updated_at;
        private String created_at;
        private int id;

        public int getPrescription_id() {
            return prescription_id;
        }

        public void setPrescription_id(int prescription_id) {
            this.prescription_id = prescription_id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDose() {
            return dose;
        }

        public void setDose(String dose) {
            this.dose = dose;
        }

        public String getResponse_date() {
            return response_date;
        }

        public void setResponse_date(String response_date) {
            this.response_date = response_date;
        }

        public String getResponse_time() {
            return response_time;
        }

        public void setResponse_time(String response_time) {
            this.response_time = response_time;
        }

        public String getResponse_dose() {
            return response_dose;
        }

        public void setResponse_dose(String response_dose) {
            this.response_dose = response_dose;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
