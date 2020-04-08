package com.imed.medcare.network.response;

import java.util.List;

public class RespondedPollResponse {


    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : responded_polls
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [[{"id":16,"measurement_id":null,"poll_id":2,"type":4,"score":100,"directed_for":2,"title":"Enfermedades","description":"Indica si se te han diagnosticado una de las siguientes enfermedades después del trasplante:","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":51,"question_id":16,"choice_id":37,"responded_poll_id":11,"value":"Ninguna","date":"2019-02-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de February 2019"}],"measurement":null,"choices":[{"id":29,"question_id":16,"value":"Virus BK","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":30,"question_id":16,"value":"Virus CMV","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":31,"question_id":16,"value":"Virus EB","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":32,"question_id":16,"value":"Hongos","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":33,"question_id":16,"value":"Hepatitis B","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":34,"question_id":16,"value":"Hepatitis C","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":35,"question_id":16,"value":"VIH","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":36,"question_id":16,"value":"Cáncer","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":37,"question_id":16,"value":"Ninguna","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":47,"question_id":16,"value":"Obstrucción urinaria crónica ","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":48,"question_id":16,"value":"Enfermedad Renal Poliquística","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":49,"question_id":16,"value":"Displasia renal de la infancia","type":1,"score":100,"status":1,"created_at":null,"updated_at":null}]},{"id":15,"measurement_id":4,"poll_id":2,"type":6,"score":100,"directed_for":2,"title":"Control médico","description":"Los controles médicos periódicos son fundamentales en el periodo post trasplante. Indica la fecha de tu último control","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":53,"question_id":15,"choice_id":null,"responded_poll_id":11,"value":null,"date":"2018-09-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de September 2018"}],"measurement":{"id":4,"name":"Control","unit":"-","unit_description":"Control médico","request_date":true,"type":2,"question_type":6,"status":1,"order":3,"created_at":null,"updated_at":null},"choices":[]},{"id":20,"measurement_id":null,"poll_id":2,"type":5,"score":100,"directed_for":2,"title":"Rechazo","description":"¿Cuántas veces has tenido rechazo?","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":58,"question_id":20,"choice_id":38,"responded_poll_id":11,"value":"No he tenido rechazo","date":"2019-02-07","score":100,"status":1,"created_at":"2019-02-07 08:46:51","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de February 2019"}],"measurement":null,"choices":[{"id":38,"question_id":20,"value":"No he tenido rechazo","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":39,"question_id":20,"value":"Una sola vez","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":40,"question_id":20,"value":"Dos veces","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":41,"question_id":20,"value":"Tres veces","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":42,"question_id":20,"value":"Más de tres veces","type":1,"score":100,"status":1,"created_at":null,"updated_at":null}]},{"id":23,"measurement_id":3,"poll_id":2,"type":2,"score":100,"directed_for":2,"title":"Estatura","description":"Si existe una nueva medición de estatura, indica el último valor y fecha","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":52,"question_id":23,"choice_id":null,"responded_poll_id":11,"value":"170","date":"2019-02-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de February 2019"}],"measurement":{"id":3,"name":"Estatura","unit":"cm","unit_description":"Centímetros","request_date":true,"type":4,"question_type":2,"status":1,"order":2,"created_at":null,"updated_at":null},"choices":[]},{"id":24,"measurement_id":1,"poll_id":2,"type":3,"score":100,"directed_for":2,"title":"Creatinina","description":"Si existe una nueva medición de Creatinina, indica el último valor y fecha","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":55,"question_id":24,"choice_id":null,"responded_poll_id":11,"value":"0.95","date":"2018-07-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de July 2018"}],"measurement":{"id":1,"name":"Creatinina","unit":"mg/dL","unit_description":"Miligramos por DL","request_date":true,"type":1,"question_type":3,"status":1,"order":4,"created_at":null,"updated_at":null},"choices":[]},{"id":22,"measurement_id":2,"poll_id":2,"type":2,"score":100,"directed_for":2,"title":"Peso","description":"Si existe una nueva medición de peso, indica el último valor y fecha","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":50,"question_id":22,"choice_id":null,"responded_poll_id":11,"value":"68","date":"2019-05-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de May 2019"}],"measurement":{"id":2,"name":"Peso","unit":"kg","unit_description":"Kilogramos","request_date":true,"type":3,"question_type":3,"status":1,"order":1,"created_at":null,"updated_at":null},"choices":[]},{"id":18,"measurement_id":7,"poll_id":2,"type":6,"score":100,"directed_for":2,"title":"Biopsia","description":"En ocasiones es necesario efectuar una biopsia renal. Si este procedimiento te ha sido efectuado, pero no ha sido registrado previamente, indica la fecha de la última biopsia","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":48,"question_id":18,"choice_id":null,"responded_poll_id":11,"value":null,"date":"2016-09-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de September 2016"}],"measurement":{"id":7,"name":"Biopsia Renal","unit":"-","unit_description":"Control de biopsia renal","request_date":true,"type":2,"question_type":6,"status":1,"order":3,"created_at":null,"updated_at":null},"choices":[]},{"id":21,"measurement_id":null,"poll_id":2,"type":6,"score":100,"directed_for":2,"title":"Hospitalización por rechazo","description":"Si has tenido rechazo, indica la fecha de la última hospitalización por rechazo","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":57,"question_id":21,"choice_id":null,"responded_poll_id":11,"value":null,"date":"1997-10-11","score":100,"status":1,"created_at":"2019-02-07 08:38:36","updated_at":"2019-02-11 17:25:19","spanishDate":"11 de October 1997"}],"measurement":null,"choices":[]},{"id":17,"measurement_id":6,"poll_id":2,"type":6,"score":100,"directed_for":2,"title":"Imagenología","description":"Indica la fecha de tu último control con Ecotomografía, Scanner o Resonancia Nuclear","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":54,"question_id":17,"choice_id":null,"responded_poll_id":11,"value":null,"date":"2017-06-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de June 2017"}],"measurement":{"id":6,"name":"Imagenología","unit":"-","unit_description":"Control de imagenología","request_date":true,"type":2,"question_type":6,"status":1,"order":3,"created_at":null,"updated_at":null},"choices":[]},{"id":19,"measurement_id":null,"poll_id":2,"type":6,"score":100,"directed_for":2,"title":"Cirugías","description":"En el caso de haber experimentado cirugías después del trasplante, indica la fecha si esta no ha sido registrada previamente","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":49,"question_id":19,"choice_id":null,"responded_poll_id":11,"value":null,"date":"2034-03-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de March 2034"}],"measurement":null,"choices":[]},{"id":25,"measurement_id":8,"poll_id":2,"type":3,"score":100,"directed_for":2,"title":"Tracolimus/Prograf","description":"Si tomas Tacrolimus (v.g.: Prograf) y existe una nueva medición, indica el último valor y fecha","interval":null,"status":1,"created_at":null,"updated_at":null,"question_order":null,"answers":[{"id":46,"question_id":25,"choice_id":null,"responded_poll_id":11,"value":"4.6","date":"2018-12-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de December 2018"}],"measurement":{"id":8,"name":"Tracolimus/Prograf","unit":"ug/ml","unit_description":"ug x ml","request_date":true,"type":1,"question_type":3,"status":1,"order":6,"created_at":null,"updated_at":null},"choices":[]}]]
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
         * id : 16
         * measurement_id : null
         * poll_id : 2
         * type : 4
         * score : 100
         * directed_for : 2
         * title : Enfermedades
         * description : Indica si se te han diagnosticado una de las siguientes enfermedades después del trasplante:
         * interval : null
         * status : 1
         * created_at : null
         * updated_at : null
         * question_order : null
         * answers : [{"id":51,"question_id":16,"choice_id":37,"responded_poll_id":11,"value":"Ninguna","date":"2019-02-07","score":100,"status":1,"created_at":"2019-02-07 08:33:52","updated_at":"2019-02-11 17:25:19","spanishDate":"07 de February 2019"}]
         * measurement : null
         * choices : [{"id":29,"question_id":16,"value":"Virus BK","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":30,"question_id":16,"value":"Virus CMV","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":31,"question_id":16,"value":"Virus EB","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":32,"question_id":16,"value":"Hongos","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":33,"question_id":16,"value":"Hepatitis B","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":34,"question_id":16,"value":"Hepatitis C","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":35,"question_id":16,"value":"VIH","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":36,"question_id":16,"value":"Cáncer","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":37,"question_id":16,"value":"Ninguna","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":47,"question_id":16,"value":"Obstrucción urinaria crónica ","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":48,"question_id":16,"value":"Enfermedad Renal Poliquística","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":49,"question_id":16,"value":"Displasia renal de la infancia","type":1,"score":100,"status":1,"created_at":null,"updated_at":null}]
         */

        private int id;
        private Object measurement_id;
        private int poll_id;
        private int type;
        private int score;
        private int directed_for;
        private String title;
        private Boolean show = true;
        private String description;
        private Object interval;
        private int status;
        private Object created_at;
        private Object updated_at;
        private Object question_order;
        private Object measurement;
        private List<AnswersBean> answers;
        private List<ChoicesBean> choices;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Boolean getShow() { return show; }

        public Object getMeasurement_id() {
            return measurement_id;
        }

        public void setMeasurement_id(Object measurement_id) {
            this.measurement_id = measurement_id;
        }

        public int getPoll_id() {
            return poll_id;
        }

        public void setPoll_id(int poll_id) {
            this.poll_id = poll_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getDirected_for() {
            return directed_for;
        }

        public void setDirected_for(int directed_for) {
            this.directed_for = directed_for;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getInterval() {
            return interval;
        }

        public void setInterval(Object interval) {
            this.interval = interval;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }

        public Object getQuestion_order() {
            return question_order;
        }

        public void setQuestion_order(Object question_order) {
            this.question_order = question_order;
        }

        public Object getMeasurement() {
            return measurement;
        }

        public void setMeasurement(Object measurement) {
            this.measurement = measurement;
        }

        public List<AnswersBean> getAnswers() {
            return answers;
        }

        public void setAnswers(List<AnswersBean> answers) {
            this.answers = answers;
        }

        public List<ChoicesBean> getChoices() {
            return choices;
        }

        public void setChoices(List<ChoicesBean> choices) {
            this.choices = choices;
        }

        public static class AnswersBean {
            /**
             * id : 51
             * question_id : 16
             * choice_id : 37
             * responded_poll_id : 11
             * value : Ninguna
             * date : 2019-02-07
             * score : 100
             * status : 1
             * created_at : 2019-02-07 08:33:52
             * updated_at : 2019-02-11 17:25:19
             * spanishDate : 07 de February 2019
             */

            private int id;
            private int question_id;
            private int choice_id;
            private int responded_poll_id;
            private String value;
            private String date;
            private int score;
            private int status;
            private String created_at;
            private String updated_at;
            private String spanishDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public int getChoice_id() {
                return choice_id;
            }

            public void setChoice_id(int choice_id) {
                this.choice_id = choice_id;
            }

            public int getResponded_poll_id() {
                return responded_poll_id;
            }

            public void setResponded_poll_id(int responded_poll_id) {
                this.responded_poll_id = responded_poll_id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
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
        }

        public static class ChoicesBean {
            /**
             * id : 29
             * question_id : 16
             * value : Virus BK
             * type : 1
             * score : 100
             * status : 1
             * created_at : null
             * updated_at : null
             */

            private int id;
            private int question_id;
            private String value;
            private int type;
            private int score;
            private int status;
            private Object created_at;
            private Object updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getCreated_at() {
                return created_at;
            }

            public void setCreated_at(Object created_at) {
                this.created_at = created_at;
            }

            public Object getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(Object updated_at) {
                this.updated_at = updated_at;
            }
        }
    }
}
