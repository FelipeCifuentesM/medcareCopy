package com.imed.medcare.network.response;

import java.util.List;

public class HistoryResponse {

    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : responded_polls
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [[{"id":11,"score":1100,"attachment":69,"current_questions":11,"total_questions":12,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","date":"Febrero 2019","remainingDays":15,"pivot":{"treatment_id":2,"responded_poll_id":11},"poll_period":{"id":3,"poll_id":2,"period":"2019-02-01","start":"2019-02-01 00:00:00","end":"2019-02-28 00:00:00","status":1,"created_at":"2019-02-06 11:42:57","updated_at":"2019-02-06 11:42:57","spanishDate":"Febrero 2019","remainingDays":"Quedan 16 días"}}]]
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
         * id : 11
         * score : 1100
         * attachment : 69
         * current_questions : 11
         * total_questions : 12
         * created_at : 2019-02-07 08:33:52
         * updated_at : 2019-02-11 17:25:19
         * date : Febrero 2019
         * remainingDays : 15
         * pivot : {"treatment_id":2,"responded_poll_id":11}
         * poll_period : {"id":3,"poll_id":2,"period":"2019-02-01","start":"2019-02-01 00:00:00","end":"2019-02-28 00:00:00","status":1,"created_at":"2019-02-06 11:42:57","updated_at":"2019-02-06 11:42:57","spanishDate":"Febrero 2019","remainingDays":"Quedan 16 días"}
         */

        private int id;
        private int score;
        private int attachment;
        private int current_questions;
        private int total_questions;
        private String created_at;
        private String updated_at;
        private String date;
        private int remainingDays;
        private PivotBean pivot;
        private PollPeriodBean poll_period;

        public int getId() {
            return id;
        }


        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getAttachment() {
            return attachment;
        }

        public void setAttachment(int attachment) {
            this.attachment = attachment;
        }

        public int getCurrent_questions() {
            return current_questions;
        }

        public void setCurrent_questions(int current_questions) {
            this.current_questions = current_questions;
        }

        public int getTotal_questions() {
            return total_questions;
        }

        public void setTotal_questions(int total_questions) {
            this.total_questions = total_questions;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getRemainingDays() {
            return remainingDays;
        }

        public void setRemainingDays(int remainingDays) {
            this.remainingDays = remainingDays;
        }

        public PivotBean getPivot() {
            return pivot;
        }

        public void setPivot(PivotBean pivot) {
            this.pivot = pivot;
        }

        public PollPeriodBean getPoll_period() {
            return poll_period;
        }

        public void setPoll_period(PollPeriodBean poll_period) {
            this.poll_period = poll_period;
        }

        public static class PivotBean {
            /**
             * treatment_id : 2
             * responded_poll_id : 11
             */

            private int treatment_id;
            private int responded_poll_id;

            public int getTreatment_id() {
                return treatment_id;
            }

            public void setTreatment_id(int treatment_id) {
                this.treatment_id = treatment_id;
            }

            public int getResponded_poll_id() {
                return responded_poll_id;
            }

            public void setResponded_poll_id(int responded_poll_id) {
                this.responded_poll_id = responded_poll_id;
            }
        }

        public static class PollPeriodBean {
            /**
             * id : 3
             * poll_id : 2
             * period : 2019-02-01
             * start : 2019-02-01 00:00:00
             * end : 2019-02-28 00:00:00
             * status : 1
             * created_at : 2019-02-06 11:42:57
             * updated_at : 2019-02-06 11:42:57
             * spanishDate : Febrero 2019
             * remainingDays : Quedan 16 días
             */

            private int id;
            private int poll_id;
            private String period;
            private String start;
            private String end;
            private int status;
            private String created_at;
            private String updated_at;
            private String spanishDate;
            private String remainingDays;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPoll_id() {
                return poll_id;
            }

            public void setPoll_id(int poll_id) {
                this.poll_id = poll_id;
            }

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
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

            public String getSpanishDate() {
                return spanishDate;
            }

            public void setSpanishDate(String spanishDate) {
                this.spanishDate = spanishDate;
            }

            public String getRemainingDays() {
                return remainingDays;
            }

            public void setRemainingDays(String remainingDays) {
                this.remainingDays = remainingDays;
            }
        }
    }
}
