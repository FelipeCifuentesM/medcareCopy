package com.imed.medcare.network.response;

import java.util.List;

public class MedicPollResponse {


    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : polls
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [{"id":2,"name":"Cuestionario médico","description":"Cuestionario para el comienzo del tratamiento","type":3,"status":1,"created_at":null,"updated_at":null,"treatment_id":2,"pivot":{"treatment_type_id":2,"poll_id":2},"questions":[{"id":13,"measurement_id":null,"poll_id":2,"type":1,"score":100,"directed_for":1,"title":"Control","description":"Como ha controlado la enfermedad","status":1,"created_at":null,"updated_at":null,"choices":[],"measurement":null,"answers":[]},{"id":14,"measurement_id":null,"poll_id":2,"type":1,"score":100,"directed_for":1,"title":"Razones","description":"Por qué","status":1,"created_at":null,"updated_at":null,"choices":[],"measurement":null,"answers":[]}]}]
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
         * id : 2
         * name : Cuestionario médico
         * description : Cuestionario para el comienzo del tratamiento
         * type : 3
         * status : 1
         * created_at : null
         * updated_at : null
         * treatment_id : 2
         * pivot : {"treatment_type_id":2,"poll_id":2}
         * questions : [{"id":13,"measurement_id":null,"poll_id":2,"type":1,"score":100,"directed_for":1,"title":"Control","description":"Como ha controlado la enfermedad","status":1,"created_at":null,"updated_at":null,"choices":[],"measurement":null,"answers":[]},{"id":14,"measurement_id":null,"poll_id":2,"type":1,"score":100,"directed_for":1,"title":"Razones","description":"Por qué","status":1,"created_at":null,"updated_at":null,"choices":[],"measurement":null,"answers":[]}]
         */

        private int id;
        private String name;
        private String description;
        private int type;
        private int status;
        private Object created_at;
        private Object updated_at;
        private int treatment_id;
        private PivotBean pivot;
        private List<QuestionsBean> questions;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getTreatment_id() {
            return treatment_id;
        }

        public void setTreatment_id(int treatment_id) {
            this.treatment_id = treatment_id;
        }

        public PivotBean getPivot() {
            return pivot;
        }

        public void setPivot(PivotBean pivot) {
            this.pivot = pivot;
        }

        public List<QuestionsBean> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuestionsBean> questions) {
            this.questions = questions;
        }

        public static class PivotBean {
            /**
             * treatment_type_id : 2
             * poll_id : 2
             */

            private int treatment_type_id;
            private int poll_id;

            public int getTreatment_type_id() {
                return treatment_type_id;
            }

            public void setTreatment_type_id(int treatment_type_id) {
                this.treatment_type_id = treatment_type_id;
            }

            public int getPoll_id() {
                return poll_id;
            }

            public void setPoll_id(int poll_id) {
                this.poll_id = poll_id;
            }
        }

        public static class QuestionsBean {
            /**
             * id : 13
             * measurement_id : null
             * poll_id : 2
             * type : 1
             * score : 100
             * directed_for : 1
             * title : Control
             * description : Como ha controlado la enfermedad
             * status : 1
             * created_at : null
             * updated_at : null
             * choices : []
             * measurement : null
             * answers : []
             */

            private int id;
            private Object measurement_id;
            private int poll_id;
            private int type;
            private int score;
            private int directed_for;
            private String title;
            private String description;
            private int status;
            private Object created_at;
            private Object updated_at;
            private MeasurementBean measurement;
            private List<ChoicesBean> choices;
            private List<AnswersBean> answers;





            public class MeasurementBean{
                int id;
                String name;
                String unit;
                String unit_description;
                Boolean request_date;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getUnit_description() {
                    return unit_description;
                }

                public void setUnit_description(String unit_description) {
                    this.unit_description = unit_description;
                }

                public Boolean getRequest_date() {
                    return request_date;
                }

                public void setRequest_date(Boolean request_date) {
                    this.request_date = request_date;
                }
            }

            public class ChoicesBean{

                int id;
                String value;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public class AnswersBean{
                int id;
                int choice_id;
                String value;
                int score;
                String date;

                public String getDate(){return date;}
                public int getId() {
                    return id;
                }
                public int getChoice_id() {
                    return choice_id;
                }

                public void setChoice_id(int choice_id) {
                    this.choice_id = choice_id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public MeasurementBean getMeasurement() {
                return measurement;
            }

            public void setMeasurement(MeasurementBean measurement) {
                this.measurement = measurement;
            }

            public List<ChoicesBean> getChoices() {
                return choices;
            }

            public void setChoices(List<ChoicesBean> choices) {
                this.choices = choices;
            }

            public List<AnswersBean> getAnswers() {
                return answers;
            }

            public void setAnswers(List<AnswersBean> answers) {
                this.answers = answers;
            }
        }
    }
}
