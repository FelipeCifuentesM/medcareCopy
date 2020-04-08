package com.imed.medcare.network.response;

import java.util.List;

public class UserPollResponse {

    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : polls
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [{"id":3,"name":"Encuesta apego","description":"Encuesta de apego para el usuario","type":1,"status":1,"created_at":null,"updated_at":null,"poll_period_id":3,"start":"2018-08-29 00:00:00","end":"2018-09-28 00:00:00","period":"2018-08-29","questions":[{"id":7,"measurement_id":null,"poll_id":3,"type":1,"score":100,"directed_for":2,"title":"Enfermedades","description":"¿Cuáles enfermedades ha tenido?","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]},{"id":8,"measurement_id":null,"poll_id":3,"type":2,"score":100,"directed_for":1,"title":"Visita","description":"A que edad fue su última visita al médico","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]},{"id":9,"measurement_id":1,"poll_id":3,"type":3,"score":100,"directed_for":1,"title":"Creatinina","description":"Cuál es su nivel de Creatinina","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]},{"id":10,"measurement_id":null,"poll_id":3,"type":4,"score":100,"directed_for":1,"title":"Medicamentos","description":"¿Qué medicamentos ha tomado alguna vez?","status":1,"created_at":null,"updated_at":null,"choices":[{"id":9,"question_id":10,"value":"Aspirina","type":1,"score":50,"status":1,"created_at":null,"updated_at":null},{"id":10,"question_id":10,"value":"Dipirona","type":1,"score":50,"status":1,"created_at":null,"updated_at":null},{"id":11,"question_id":10,"value":"Ibuprofeno","type":1,"score":50,"status":1,"created_at":null,"updated_at":null},{"id":12,"question_id":10,"value":"Panadol","type":1,"score":50,"status":1,"created_at":null,"updated_at":null}],"answers":[]},{"id":11,"measurement_id":null,"poll_id":3,"type":5,"score":100,"directed_for":1,"title":"Clínica","description":"Seleccione la clínica de preferencia","status":1,"created_at":null,"updated_at":null,"choices":[{"id":13,"question_id":11,"value":"Clínica Dávila","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":14,"question_id":11,"value":"Clínica Reñaca","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":15,"question_id":11,"value":"Clínica Los Carrera","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":16,"question_id":11,"value":"Clínica Valparaíso","type":1,"score":100,"status":1,"created_at":null,"updated_at":null}],"answers":[]},{"id":12,"measurement_id":null,"poll_id":3,"type":6,"score":100,"directed_for":1,"title":"App","description":"¿Te gusta la aplicación?","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]}]}]
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
         * id : 3
         * name : Encuesta apego
         * description : Encuesta de apego para el usuario
         * type : 1
         * status : 1
         * created_at : null
         * updated_at : null
         * poll_period_id : 3
         * start : 2018-08-29 00:00:00
         * end : 2018-09-28 00:00:00
         * period : 2018-08-29
         * questions : [{"id":7,"measurement_id":null,"poll_id":3,"type":1,"score":100,"directed_for":2,"title":"Enfermedades","description":"¿Cuáles enfermedades ha tenido?","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]},{"id":8,"measurement_id":null,"poll_id":3,"type":2,"score":100,"directed_for":1,"title":"Visita","description":"A que edad fue su última visita al médico","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]},{"id":9,"measurement_id":1,"poll_id":3,"type":3,"score":100,"directed_for":1,"title":"Creatinina","description":"Cuál es su nivel de Creatinina","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]},{"id":10,"measurement_id":null,"poll_id":3,"type":4,"score":100,"directed_for":1,"title":"Medicamentos","description":"¿Qué medicamentos ha tomado alguna vez?","status":1,"created_at":null,"updated_at":null,"choices":[{"id":9,"question_id":10,"value":"Aspirina","type":1,"score":50,"status":1,"created_at":null,"updated_at":null},{"id":10,"question_id":10,"value":"Dipirona","type":1,"score":50,"status":1,"created_at":null,"updated_at":null},{"id":11,"question_id":10,"value":"Ibuprofeno","type":1,"score":50,"status":1,"created_at":null,"updated_at":null},{"id":12,"question_id":10,"value":"Panadol","type":1,"score":50,"status":1,"created_at":null,"updated_at":null}],"answers":[]},{"id":11,"measurement_id":null,"poll_id":3,"type":5,"score":100,"directed_for":1,"title":"Clínica","description":"Seleccione la clínica de preferencia","status":1,"created_at":null,"updated_at":null,"choices":[{"id":13,"question_id":11,"value":"Clínica Dávila","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":14,"question_id":11,"value":"Clínica Reñaca","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":15,"question_id":11,"value":"Clínica Los Carrera","type":1,"score":100,"status":1,"created_at":null,"updated_at":null},{"id":16,"question_id":11,"value":"Clínica Valparaíso","type":1,"score":100,"status":1,"created_at":null,"updated_at":null}],"answers":[]},{"id":12,"measurement_id":null,"poll_id":3,"type":6,"score":100,"directed_for":1,"title":"App","description":"¿Te gusta la aplicación?","status":1,"created_at":null,"updated_at":null,"choices":[],"answers":[]}]
         */

        private int id;
        private String name;
        private String description;
        private int type;
        private int status;
        private Object created_at;
        private Object updated_at;
        private int poll_period_id;
        private String start;
        private String end;
        private String period;
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

        public int getPoll_period_id() {
            return poll_period_id;
        }

        public void setPoll_period_id(int poll_period_id) {
            this.poll_period_id = poll_period_id;
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

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public List<QuestionsBean> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuestionsBean> questions) {
            this.questions = questions;
        }

        public static class QuestionsBean {
            /**
             * id : 7
             * measurement_id : null
             * poll_id : 3
             * type : 1
             * score : 100
             * directed_for : 2
             * title : Enfermedades
             * description : ¿Cuáles enfermedades ha tenido?
             * status : 1
             * created_at : null
             * updated_at : null
             * choices : []
             * answers : []
             */

            private int id;
            private Integer measurement_id;
            private int poll_id;
            private int type;
            private Integer question_order;
            private int score;
            private int directed_for;
            private String title;
            private String description;
            private int status;
            private Object created_at;
            private Object updated_at;
            private List<ChoicesBean> choices;
            private List<AnswersBean> answers;
            private MeasurementBean measurement;


            public int getId() {
                return id;
            }

            public Integer getQuestion_order() { return question_order; }

            public void setId(int id) {
                this.id = id;
            }

            public Integer getMeasurement_id() {
                return measurement_id;
            }

            public void setMeasurement_id(Integer measurement_id) {
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

            public List<ChoicesBean> getChoices() {
                return choices;
            }

            public MeasurementBean getMeasurement(){return measurement;}

            public void setChoices(List<ChoicesBean> choices) {
                this.choices = choices;
            }

            public List<AnswersBean> getAnswers() {
                return answers;
            }

            public void setAnswers(List<AnswersBean> answers) {
                this.answers = answers;
            }


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
                int choice_id;
                String value;
                int score;
                int id;
                String date;

                public String getDate(){return  date;}
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
        }
    }
}
