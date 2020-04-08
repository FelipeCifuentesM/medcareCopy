package com.imed.medcare.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {


    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : profile
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [{"user":{"inum":null,"username":"16500233-4","name":"Vicente Fernandez","names":{"familyName":"Fernandez","givenName":"Vicente","formatted":"nano vicencio"},"email":"fvicencio@jumpitt.com","phone":"12345678","avatar":"http://mimed.jumpittlabs.cl/avatar/placeholder.png","id":1,"measurements":[{"id":2,"name":"Peso","unit":"kg","unit_description":"Kilogramos","request_date":false,"type":3,"question_type":2,"status":1,"created_at":null,"updated_at":null,"treatment_measurements":[{"id":44,"user_id":1,"answer_id":22,"measurement_id":2,"poll_period_id":2,"date":"2018-10-22","value":"22","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}],"pivot":{"user_id":1,"measurement_id":2}},{"id":7,"name":"Biopsia Renal","unit":"-","unit_description":"Control de biopsia renal","request_date":true,"type":2,"question_type":6,"status":1,"created_at":null,"updated_at":null,"treatment_measurements":[{"id":45,"user_id":1,"answer_id":23,"measurement_id":7,"poll_period_id":2,"date":"2018-10-22","value":"Hecho","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}],"pivot":{"user_id":1,"measurement_id":7}}]},"attachment_status":100,"professionals":[{"id":1,"forenames":"Juan","surnames":"Lopez","specialty":"Medicina","avatar":"$2y$10$V5qUbGM32Sd5-NxUzSGpgOS2M-kcy-AeFSgQrcvjUK9OXW2MJKryi.jpg","status":2,"avatarUrl":"http://medcare.jumpittlabs.cl/professional/avatar/$2y$10$V5qUbGM32Sd5-NxUzSGpgOS2M-kcy-AeFSgQrcvjUK9OXW2MJKryi.jpg"}]}]
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
         * user : {"inum":null,"username":"16500233-4","name":"Vicente Fernandez","names":{"familyName":"Fernandez","givenName":"Vicente","formatted":"nano vicencio"},"email":"fvicencio@jumpitt.com","phone":"12345678","avatar":"http://mimed.jumpittlabs.cl/avatar/placeholder.png","id":1,"measurements":[{"id":2,"name":"Peso","unit":"kg","unit_description":"Kilogramos","request_date":false,"type":3,"question_type":2,"status":1,"created_at":null,"updated_at":null,"treatment_measurements":[{"id":44,"user_id":1,"answer_id":22,"measurement_id":2,"poll_period_id":2,"date":"2018-10-22","value":"22","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}],"pivot":{"user_id":1,"measurement_id":2}},{"id":7,"name":"Biopsia Renal","unit":"-","unit_description":"Control de biopsia renal","request_date":true,"type":2,"question_type":6,"status":1,"created_at":null,"updated_at":null,"treatment_measurements":[{"id":45,"user_id":1,"answer_id":23,"measurement_id":7,"poll_period_id":2,"date":"2018-10-22","value":"Hecho","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}],"pivot":{"user_id":1,"measurement_id":7}}]}
         * attachment_status : 100
         * professionals : [{"id":1,"forenames":"Juan","surnames":"Lopez","specialty":"Medicina","avatar":"$2y$10$V5qUbGM32Sd5-NxUzSGpgOS2M-kcy-AeFSgQrcvjUK9OXW2MJKryi.jpg","status":2,"avatarUrl":"http://medcare.jumpittlabs.cl/professional/avatar/$2y$10$V5qUbGM32Sd5-NxUzSGpgOS2M-kcy-AeFSgQrcvjUK9OXW2MJKryi.jpg"}]
         */

        private UserBean user;
        private int attachment_status;
        private List<ProfessionalsBean> professionals;
        private Attachment attachment;

        public Attachment getAttachment() {
            return attachment;
        }

        public static class Attachment{
            @SerializedName("available_poll")
            private boolean availablePoll;
            private String message;
            @SerializedName("current_score")
            private Integer currentScore;


            public boolean isAvailablePoll() {
                return availablePoll;
            }

            public String getMessage() {
                return message;
            }

            public Integer getCurrentScore() {
                return currentScore;
            }

        }


        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getAttachment_status() {
            return attachment_status;
        }

        public void setAttachment_status(int attachment_status) {
            this.attachment_status = attachment_status;
        }

        public List<ProfessionalsBean> getProfessionals() {
            return professionals;
        }

        public void setProfessionals(List<ProfessionalsBean> professionals) {
            this.professionals = professionals;
        }

        public static class UserBean {
            /**
             * inum : null
             * username : 16500233-4
             * name : Vicente Fernandez
             * names : {"familyName":"Fernandez","givenName":"Vicente","formatted":"nano vicencio"}
             * email : fvicencio@jumpitt.com
             * phone : 12345678
             * avatar : http://mimed.jumpittlabs.cl/avatar/placeholder.png
             * id : 1
             * measurements : [{"id":2,"name":"Peso","unit":"kg","unit_description":"Kilogramos","request_date":false,"type":3,"question_type":2,"status":1,"created_at":null,"updated_at":null,"treatment_measurements":[{"id":44,"user_id":1,"answer_id":22,"measurement_id":2,"poll_period_id":2,"date":"2018-10-22","value":"22","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}],"pivot":{"user_id":1,"measurement_id":2}},{"id":7,"name":"Biopsia Renal","unit":"-","unit_description":"Control de biopsia renal","request_date":true,"type":2,"question_type":6,"status":1,"created_at":null,"updated_at":null,"treatment_measurements":[{"id":45,"user_id":1,"answer_id":23,"measurement_id":7,"poll_period_id":2,"date":"2018-10-22","value":"Hecho","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}],"pivot":{"user_id":1,"measurement_id":7}}]
             */

            private Object inum;
            private String username;
            private String name;
            private NamesBean names;
            private String email;
            private String phone;
            private String avatar;
            private int id;
            private List<MeasurementsBean> measurements;
            private String message;


            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
            public Object getInum() {
                return inum;
            }

            public void setInum(Object inum) {
                this.inum = inum;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public NamesBean getNames() {
                return names;
            }

            public void setNames(NamesBean names) {
                this.names = names;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<MeasurementsBean> getMeasurements() {
                return measurements;
            }

            public void setMeasurements(List<MeasurementsBean> measurements) {
                this.measurements = measurements;
            }

            public static class NamesBean {
                /**
                 * familyName : Fernandez
                 * givenName : Vicente
                 * formatted : nano vicencio
                 */

                private String familyName;
                private String givenName;
                private String formatted;

                public String getFamilyName() {
                    return familyName;
                }

                public void setFamilyName(String familyName) {
                    this.familyName = familyName;
                }

                public String getGivenName() {
                    return givenName;
                }

                public void setGivenName(String givenName) {
                    this.givenName = givenName;
                }

                public String getFormatted() {
                    return formatted;
                }

                public void setFormatted(String formatted) {
                    this.formatted = formatted;
                }
            }

            public static class MeasurementsBean {
                /**
                 * id : 2
                 * name : Peso
                 * unit : kg
                 * unit_description : Kilogramos
                 * request_date : false
                 * type : 3
                 * question_type : 2
                 * status : 1
                 * created_at : null
                 * updated_at : null
                 * treatment_measurements : [{"id":44,"user_id":1,"answer_id":22,"measurement_id":2,"poll_period_id":2,"date":"2018-10-22","value":"22","created_at":"2018-10-22 11:25:40","updated_at":"2018-10-22 11:25:40","rut":null}]
                 * pivot : {"user_id":1,"measurement_id":2}
                 */

                private int id;
                private String name;
                private String unit;
                private String unit_description;
                private boolean request_date;
                private int type;
                private int question_type;
                private int status;
                private Object created_at;
                private Object updated_at;
                private PivotBean pivot;
                private List<TreatmentMeasurementsBean> treatment_measurements;

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

                public boolean isRequest_date() {
                    return request_date;
                }

                public void setRequest_date(boolean request_date) {
                    this.request_date = request_date;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getQuestion_type() {
                    return question_type;
                }

                public void setQuestion_type(int question_type) {
                    this.question_type = question_type;
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

                public PivotBean getPivot() {
                    return pivot;
                }

                public void setPivot(PivotBean pivot) {
                    this.pivot = pivot;
                }

                public List<TreatmentMeasurementsBean> getTreatment_measurements() {
                    return treatment_measurements;
                }

                public void setTreatment_measurements(List<TreatmentMeasurementsBean> treatment_measurements) {
                    this.treatment_measurements = treatment_measurements;
                }

                public static class PivotBean {
                    /**
                     * user_id : 1
                     * measurement_id : 2
                     */

                    private int user_id;
                    private int measurement_id;

                    public int getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(int user_id) {
                        this.user_id = user_id;
                    }

                    public int getMeasurement_id() {
                        return measurement_id;
                    }

                    public void setMeasurement_id(int measurement_id) {
                        this.measurement_id = measurement_id;
                    }
                }

                public static class TreatmentMeasurementsBean {
                    /**
                     * id : 44
                     * user_id : 1
                     * answer_id : 22
                     * measurement_id : 2
                     * poll_period_id : 2
                     * date : 2018-10-22
                     * value : 22
                     * created_at : 2018-10-22 11:25:40
                     * updated_at : 2018-10-22 11:25:40
                     * rut : null
                     */

                    private int id;
                    private int user_id;
                    private int answer_id;
                    private int measurement_id;
                    private int poll_period_id;
                    private String date;
                    private String value;
                    private String created_at;
                    private String updated_at;
                    private Object rut;

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

                    public int getAnswer_id() {
                        return answer_id;
                    }

                    public void setAnswer_id(int answer_id) {
                        this.answer_id = answer_id;
                    }

                    public int getMeasurement_id() {
                        return measurement_id;
                    }

                    public void setMeasurement_id(int measurement_id) {
                        this.measurement_id = measurement_id;
                    }

                    public int getPoll_period_id() {
                        return poll_period_id;
                    }

                    public void setPoll_period_id(int poll_period_id) {
                        this.poll_period_id = poll_period_id;
                    }

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
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

                    public Object getRut() {
                        return rut;
                    }

                    public void setRut(Object rut) {
                        this.rut = rut;
                    }
                }
            }
        }

        public static class ProfessionalsBean {
            /**
             * id : 1
             * forenames : Juan
             * surnames : Lopez
             * specialty : Medicina
             * avatar : $2y$10$V5qUbGM32Sd5-NxUzSGpgOS2M-kcy-AeFSgQrcvjUK9OXW2MJKryi.jpg
             * status : 2
             * avatarUrl : http://medcare.jumpittlabs.cl/professional/avatar/$2y$10$V5qUbGM32Sd5-NxUzSGpgOS2M-kcy-AeFSgQrcvjUK9OXW2MJKryi.jpg
             */

            private int id;
            private String forenames;
            private String surnames;
            private String specialty;
            private String avatar;
            private int status;
            private String avatarUrl;
            private String prefix;
            private String branch;
            private String entity;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getForenames() {
                return forenames;
            }

            public void setForenames(String forenames) {
                this.forenames = forenames;
            }

            public String getSurnames() {
                return surnames;
            }

            public void setSurnames(String surnames) {
                this.surnames = surnames;
            }

            public String getSpecialty() {
                return specialty;
            }

            public void setSpecialty(String specialty) {
                this.specialty = specialty;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getPrefix() {
                return prefix;
            }

            public String getBranch() {
                return branch;
            }

            public String getEntity() {
                return entity;
            }

            public void setEntity(String entity) {
                this.entity = entity;
            }


            public void setBranch(String branch) {
                this.branch = branch;
            }
            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }
    }
}
