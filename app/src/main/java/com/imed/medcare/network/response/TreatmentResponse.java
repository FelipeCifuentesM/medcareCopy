package com.imed.medcare.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TreatmentResponse {

    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : treatments
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : [{"id":3,"start":"2018-12-26 00:00:00","updated_at":"2018-12-26 17:02:11","end":null,"user_id":3,"endDate":"indefinido","treatment_type":{"id":2,"name":"Trasplante","status":1,"created_at":null,"updated_at":null},"patient":{"id":3,"membership_id":1,"user_id":3,"invitation_cuid":"18237990-5","invitation_name":"el danilo","type":1,"status":1,"created_at":"2018-12-26 16:59:05","updated_at":"2018-12-26 17:02:47","membership":{"id":1,"professional_id":1,"department_id":1,"email":"jlopez@mail.com","status":1,"created_at":null,"updated_at":null,"phone":"","professional":{"id":1,"forenames":"Jorge","surnames":"Morales","avatar":"default-avatar.png","specialty":"Medicina","type":1,"status":1,"created_at":null,"updated_at":null,"rut":"","phone":"","email":"","prefix":"","avatarUrl":"http://localhost:8000/professional/avatar/default-avatar.png","fullName":" Jorge Morales","formatedRut":"0-"},"department":{"id":1,"branch_id":1,"name":"pediatría","description":"sala de pediatría","status":1,"created_at":null,"updated_at":null,"branch":{"id":1,"entity_id":1,"name":"Clínica Las Condes","description":"Casa Matriz","address":"Las Condes, Santiago","lat":"-71.62803","lng":"-33.02982","status":1,"created_at":null,"updated_at":null,"entity":{"id":1,"name":"Clínica Las Condes","status":1,"created_at":null,"updated_at":null}}}}},"prescriptions":[{"id":4,"treatment_id":3,"medicament_id":21,"medicament_format_id":3,"start":"2018-12-01 03:00:00","end":null,"type":1,"status":1,"frequency":1,"permanent":true,"dosis":1,"interval":1,"days":null,"last_take":"2018-12-01 03:00:00","medicament_format":{"id":3,"name":"Cápsula","img":"/images/default-medicament.jpg"},"medicament":{"name":"prograf 5 mg cápsula (Gador)","grams":0,"image":"","concept_id":"863221000167106"},"schedules":[{"id":14,"prescription_id":4,"type":1,"day":null,"hour":"17:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:11","updated_at":"2018-12-26 17:02:11"}]},{"id":5,"treatment_id":3,"medicament_id":22,"medicament_format_id":2,"start":"2018-12-01 03:00:00","end":"2018-12-16 03:00:00","type":1,"status":1,"frequency":2,"permanent":false,"dosis":1,"interval":1,"days":"[1,4]","last_take":"2018-12-01 03:00:00","medicament_format":{"id":2,"name":"Comprimido","img":"/images/default-medicament.jpg"},"medicament":{"name":"valganciclovir 450 mg comprimido","grams":0,"image":"","concept_id":"689101000167103"},"schedules":[{"id":15,"prescription_id":5,"type":1,"day":null,"hour":"18:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"},{"id":16,"prescription_id":5,"type":2,"day":1,"hour":null,"dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"},{"id":17,"prescription_id":5,"type":2,"day":4,"hour":null,"dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"}]},{"id":6,"treatment_id":3,"medicament_id":23,"medicament_format_id":1,"start":"2018-12-01 03:00:00","end":"2018-12-22 03:00:00","type":1,"status":1,"frequency":3,"permanent":false,"dosis":1,"interval":2,"days":null,"last_take":"2018-12-01 03:00:00","medicament_format":{"id":1,"name":"Comprimido Recubierto","img":"/images/default-medicament.jpg"},"medicament":{"name":"valsartán 320 mg comprimido recubierto (Euromed)","grams":0,"image":"","concept_id":"818171000167108"},"schedules":[{"id":18,"prescription_id":6,"type":1,"day":null,"hour":"19:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"},{"id":19,"prescription_id":6,"type":3,"day":2,"hour":null,"dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"}]}],"docs":[]}]
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
         * start : 2018-12-26 00:00:00
         * updated_at : 2018-12-26 17:02:11
         * end : null
         * user_id : 3
         * endDate : indefinido
         * treatment_type : {"id":2,"name":"Trasplante","status":1,"created_at":null,"updated_at":null}
         * patient : {"id":3,"membership_id":1,"user_id":3,"invitation_cuid":"18237990-5","invitation_name":"el danilo","type":1,"status":1,"created_at":"2018-12-26 16:59:05","updated_at":"2018-12-26 17:02:47","membership":{"id":1,"professional_id":1,"department_id":1,"email":"jlopez@mail.com","status":1,"created_at":null,"updated_at":null,"phone":"","professional":{"id":1,"forenames":"Jorge","surnames":"Morales","avatar":"default-avatar.png","specialty":"Medicina","type":1,"status":1,"created_at":null,"updated_at":null,"rut":"","phone":"","email":"","prefix":"","avatarUrl":"http://localhost:8000/professional/avatar/default-avatar.png","fullName":" Jorge Morales","formatedRut":"0-"},"department":{"id":1,"branch_id":1,"name":"pediatría","description":"sala de pediatría","status":1,"created_at":null,"updated_at":null,"branch":{"id":1,"entity_id":1,"name":"Clínica Las Condes","description":"Casa Matriz","address":"Las Condes, Santiago","lat":"-71.62803","lng":"-33.02982","status":1,"created_at":null,"updated_at":null,"entity":{"id":1,"name":"Clínica Las Condes","status":1,"created_at":null,"updated_at":null}}}}}
         * prescriptions : [{"id":4,"treatment_id":3,"medicament_id":21,"medicament_format_id":3,"start":"2018-12-01 03:00:00","end":null,"type":1,"status":1,"frequency":1,"permanent":true,"dosis":1,"interval":1,"days":null,"last_take":"2018-12-01 03:00:00","medicament_format":{"id":3,"name":"Cápsula","img":"/images/default-medicament.jpg"},"medicament":{"name":"prograf 5 mg cápsula (Gador)","grams":0,"image":"","concept_id":"863221000167106"},"schedules":[{"id":14,"prescription_id":4,"type":1,"day":null,"hour":"17:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:11","updated_at":"2018-12-26 17:02:11"}]},{"id":5,"treatment_id":3,"medicament_id":22,"medicament_format_id":2,"start":"2018-12-01 03:00:00","end":"2018-12-16 03:00:00","type":1,"status":1,"frequency":2,"permanent":false,"dosis":1,"interval":1,"days":"[1,4]","last_take":"2018-12-01 03:00:00","medicament_format":{"id":2,"name":"Comprimido","img":"/images/default-medicament.jpg"},"medicament":{"name":"valganciclovir 450 mg comprimido","grams":0,"image":"","concept_id":"689101000167103"},"schedules":[{"id":15,"prescription_id":5,"type":1,"day":null,"hour":"18:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"},{"id":16,"prescription_id":5,"type":2,"day":1,"hour":null,"dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"},{"id":17,"prescription_id":5,"type":2,"day":4,"hour":null,"dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"}]},{"id":6,"treatment_id":3,"medicament_id":23,"medicament_format_id":1,"start":"2018-12-01 03:00:00","end":"2018-12-22 03:00:00","type":1,"status":1,"frequency":3,"permanent":false,"dosis":1,"interval":2,"days":null,"last_take":"2018-12-01 03:00:00","medicament_format":{"id":1,"name":"Comprimido Recubierto","img":"/images/default-medicament.jpg"},"medicament":{"name":"valsartán 320 mg comprimido recubierto (Euromed)","grams":0,"image":"","concept_id":"818171000167108"},"schedules":[{"id":18,"prescription_id":6,"type":1,"day":null,"hour":"19:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"},{"id":19,"prescription_id":6,"type":3,"day":2,"hour":null,"dose":"1","status":1,"created_at":"2018-12-26 17:02:12","updated_at":"2018-12-26 17:02:12"}]}]
         * docs : []
         */

        private int id;
        private String start;
        private String updated_at;
        private String end;
        private int user_id;
        private String endDate;
        private TreatmentTypeBean treatment_type;
        private PatientBean patient;
        private List<PrescriptionsBean> prescriptions;
        private List<DocsBean> docs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public TreatmentTypeBean getTreatment_type() {
            return treatment_type;
        }

        public void setTreatment_type(TreatmentTypeBean treatment_type) {
            this.treatment_type = treatment_type;
        }

        public PatientBean getPatient() {
            return patient;
        }

        public void setPatient(PatientBean patient) {
            this.patient = patient;
        }

        public List<PrescriptionsBean> getPrescriptions() {
            return prescriptions;
        }

        public void setPrescriptions(List<PrescriptionsBean> prescriptions) {
            this.prescriptions = prescriptions;
        }

        public List<DocsBean> getDocs() {
            return docs;
        }

        public void setDocs(List<DocsBean> docs) {
            this.docs = docs;
        }

        public static class TreatmentTypeBean {
            /**
             * id : 2
             * name : Trasplante
             * status : 1
             * created_at : null
             * updated_at : null
             */

            private int id;
            private String name;
            private int status;
            private Object created_at;
            private Object updated_at;

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

        public static class PatientBean {
            /**
             * id : 3
             * membership_id : 1
             * user_id : 3
             * invitation_cuid : 18237990-5
             * invitation_name : el danilo
             * type : 1
             * status : 1
             * created_at : 2018-12-26 16:59:05
             * updated_at : 2018-12-26 17:02:47
             * membership : {"id":1,"professional_id":1,"department_id":1,"email":"jlopez@mail.com","status":1,"created_at":null,"updated_at":null,"phone":"","professional":{"id":1,"forenames":"Jorge","surnames":"Morales","avatar":"default-avatar.png","specialty":"Medicina","type":1,"status":1,"created_at":null,"updated_at":null,"rut":"","phone":"","email":"","prefix":"","avatarUrl":"http://localhost:8000/professional/avatar/default-avatar.png","fullName":" Jorge Morales","formatedRut":"0-"},"department":{"id":1,"branch_id":1,"name":"pediatría","description":"sala de pediatría","status":1,"created_at":null,"updated_at":null,"branch":{"id":1,"entity_id":1,"name":"Clínica Las Condes","description":"Casa Matriz","address":"Las Condes, Santiago","lat":"-71.62803","lng":"-33.02982","status":1,"created_at":null,"updated_at":null,"entity":{"id":1,"name":"Clínica Las Condes","status":1,"created_at":null,"updated_at":null}}}}
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
            private MembershipBean membership;

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

            public MembershipBean getMembership() {
                return membership;
            }

            public void setMembership(MembershipBean membership) {
                this.membership = membership;
            }

            public static class MembershipBean {
                /**
                 * id : 1
                 * professional_id : 1
                 * department_id : 1
                 * email : jlopez@mail.com
                 * status : 1
                 * created_at : null
                 * updated_at : null
                 * phone :
                 * professional : {"id":1,"forenames":"Jorge","surnames":"Morales","avatar":"default-avatar.png","specialty":"Medicina","type":1,"status":1,"created_at":null,"updated_at":null,"rut":"","phone":"","email":"","prefix":"","avatarUrl":"http://localhost:8000/professional/avatar/default-avatar.png","fullName":" Jorge Morales","formatedRut":"0-"}
                 * department : {"id":1,"branch_id":1,"name":"pediatría","description":"sala de pediatría","status":1,"created_at":null,"updated_at":null,"branch":{"id":1,"entity_id":1,"name":"Clínica Las Condes","description":"Casa Matriz","address":"Las Condes, Santiago","lat":"-71.62803","lng":"-33.02982","status":1,"created_at":null,"updated_at":null,"entity":{"id":1,"name":"Clínica Las Condes","status":1,"created_at":null,"updated_at":null}}}
                 */

                private int id;
                private int professional_id;
                private int department_id;
                private String email;
                private int status;
                private Object created_at;
                private Object updated_at;
                private String phone;
                private ProfessionalBean professional;
                private DepartmentBean department;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getProfessional_id() {
                    return professional_id;
                }

                public void setProfessional_id(int professional_id) {
                    this.professional_id = professional_id;
                }

                public int getDepartment_id() {
                    return department_id;
                }

                public void setDepartment_id(int department_id) {
                    this.department_id = department_id;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
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

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public ProfessionalBean getProfessional() {
                    return professional;
                }

                public void setProfessional(ProfessionalBean professional) {
                    this.professional = professional;
                }

                public DepartmentBean getDepartment() {
                    return department;
                }

                public void setDepartment(DepartmentBean department) {
                    this.department = department;
                }

                public static class ProfessionalBean {
                    /**
                     * id : 1
                     * forenames : Jorge
                     * surnames : Morales
                     * avatar : default-avatar.png
                     * specialty : Medicina
                     * type : 1
                     * status : 1
                     * created_at : null
                     * updated_at : null
                     * rut :
                     * phone :
                     * email :
                     * prefix :
                     * avatarUrl : http://localhost:8000/professional/avatar/default-avatar.png
                     * fullName :  Jorge Morales
                     * formatedRut : 0-
                     */

                    private int id;
                    private String forenames;
                    private String surnames;
                    private String avatar;
                    private String specialty;
                    private int type;
                    private int status;
                    private Object created_at;
                    private Object updated_at;
                    private String rut;
                    private String phone;
                    private String email;
                    private String prefix;
                    private String avatarUrl;
                    private String fullName;
                    private String formatedRut;

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

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getSpecialty() {
                        return specialty;
                    }

                    public void setSpecialty(String specialty) {
                        this.specialty = specialty;
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

                    public String getRut() {
                        return rut;
                    }

                    public void setRut(String rut) {
                        this.rut = rut;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public String getPrefix() {
                        return prefix;
                    }

                    public void setPrefix(String prefix) {
                        this.prefix = prefix;
                    }

                    public String getAvatarUrl() {
                        return avatarUrl;
                    }

                    public void setAvatarUrl(String avatarUrl) {
                        this.avatarUrl = avatarUrl;
                    }

                    public String getFullName() {
                        return fullName;
                    }

                    public void setFullName(String fullName) {
                        this.fullName = fullName;
                    }

                    public String getFormatedRut() {
                        return formatedRut;
                    }

                    public void setFormatedRut(String formatedRut) {
                        this.formatedRut = formatedRut;
                    }
                }

                public static class DepartmentBean {
                    /**
                     * id : 1
                     * branch_id : 1
                     * name : pediatría
                     * description : sala de pediatría
                     * status : 1
                     * created_at : null
                     * updated_at : null
                     * branch : {"id":1,"entity_id":1,"name":"Clínica Las Condes","description":"Casa Matriz","address":"Las Condes, Santiago","lat":"-71.62803","lng":"-33.02982","status":1,"created_at":null,"updated_at":null,"entity":{"id":1,"name":"Clínica Las Condes","status":1,"created_at":null,"updated_at":null}}
                     */

                    private int id;
                    private int branch_id;
                    private String name;
                    private String description;
                    private int status;
                    private Object created_at;
                    private Object updated_at;
                    private BranchBean branch;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getBranch_id() {
                        return branch_id;
                    }

                    public void setBranch_id(int branch_id) {
                        this.branch_id = branch_id;
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

                    public BranchBean getBranch() {
                        return branch;
                    }

                    public void setBranch(BranchBean branch) {
                        this.branch = branch;
                    }

                    public static class BranchBean {
                        /**
                         * id : 1
                         * entity_id : 1
                         * name : Clínica Las Condes
                         * description : Casa Matriz
                         * address : Las Condes, Santiago
                         * lat : -71.62803
                         * lng : -33.02982
                         * status : 1
                         * created_at : null
                         * updated_at : null
                         * entity : {"id":1,"name":"Clínica Las Condes","status":1,"created_at":null,"updated_at":null}
                         */

                        private int id;
                        private int entity_id;
                        private String name;
                        private String description;
                        private String address;
                        private String lat;
                        private String lng;
                        private int status;
                        private Object created_at;
                        private Object updated_at;
                        private EntityBean entity;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public int getEntity_id() {
                            return entity_id;
                        }

                        public void setEntity_id(int entity_id) {
                            this.entity_id = entity_id;
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

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }

                        public String getLat() {
                            return lat;
                        }

                        public void setLat(String lat) {
                            this.lat = lat;
                        }

                        public String getLng() {
                            return lng;
                        }

                        public void setLng(String lng) {
                            this.lng = lng;
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

                        public EntityBean getEntity() {
                            return entity;
                        }

                        public void setEntity(EntityBean entity) {
                            this.entity = entity;
                        }

                        public static class EntityBean {
                            /**
                             * id : 1
                             * name : Clínica Las Condes
                             * status : 1
                             * created_at : null
                             * updated_at : null
                             */

                            private int id;
                            private String name;
                            private int status;
                            private Object created_at;
                            private Object updated_at;

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
            }
        }


        public static class DocsBean {
            /**
             * id : 3
             * name : Artboard 2 Copy (1).png
             * extension : png
             * path : f94afa9b4a9487578f104b12a419fbbd_Artboard 2 Copy (1).png
             * description : app upload
             * created_at : 2018-08-22 20:45:02
             * updated_at : 2018-08-22 20:45:02
             * pivot : {"treatment_id":2,"doc_id":3}
             */

            private int id;
            private String name;
            private String extension;
            private String path;
            private String description;
            private String created_at;
            private String updated_at;
            private PivotBean pivot;

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

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public PivotBean getPivot() {
                return pivot;
            }

            public void setPivot(PivotBean pivot) {
                this.pivot = pivot;
            }

            public static class PivotBean {
                /**
                 * treatment_id : 2
                 * doc_id : 3
                 */

                private int treatment_id;
                private int doc_id;

                public int getTreatment_id() {
                    return treatment_id;
                }

                public void setTreatment_id(int treatment_id) {
                    this.treatment_id = treatment_id;
                }

                public int getDoc_id() {
                    return doc_id;
                }

                public void setDoc_id(int doc_id) {
                    this.doc_id = doc_id;
                }
            }
        }


        public static class PrescriptionsBean {
            /**
             * id : 4
             * treatment_id : 3
             * medicament_id : 21
             * medicament_format_id : 3
             * start : 2018-12-01 03:00:00
             * end : null
             * type : 1
             * status : 1
             * frequency : 1
             * permanent : true
             * dosis : 1
             * interval : 1
             * days : null
             * last_take : 2018-12-01 03:00:00
             * medicament_format : {"id":3,"name":"Cápsula","img":"/images/default-medicament.jpg"}
             * medicament : {"name":"prograf 5 mg cápsula (Gador)","grams":0,"image":"","concept_id":"863221000167106"}
             * schedules : [{"id":14,"prescription_id":4,"type":1,"day":null,"hour":"17:00:00","dose":"1","status":1,"created_at":"2018-12-26 17:02:11","updated_at":"2018-12-26 17:02:11"}]
             */

            private int id;
            private int treatment_id;
            private int medicament_id;
            private int medicament_format_id;
            private String start;
            private String end;
            private int type;
            private int status;
            private int frequency;
            private boolean permanent;
            private int dosis;
            private int interval;
            private Object days;
            private String last_take;
            private Integer priority;
            private MedicamentFormatBean medicament_format;
            private MedicamentBean medicament;
            private List<SchedulesBean> schedules;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTreatment_id() {
                return treatment_id;
            }

            public void setTreatment_id(int treatment_id) {
                this.treatment_id = treatment_id;
            }

            public int getMedicament_id() {
                return medicament_id;
            }

            public void setMedicament_id(int medicament_id) {
                this.medicament_id = medicament_id;
            }

            public int getMedicament_format_id() {
                return medicament_format_id;
            }

            public void setMedicament_format_id(int medicament_format_id) { this.medicament_format_id = medicament_format_id; }
            public Integer getPriority() { return priority; }

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

            public int getFrequency() {
                return frequency;
            }

            public void setFrequency(int frequency) {
                this.frequency = frequency;
            }

            public boolean isPermanent() {
                return permanent;
            }

            public void setPermanent(boolean permanent) {
                this.permanent = permanent;
            }

            public int getDosis() {
                return dosis;
            }

            public void setDosis(int dosis) {
                this.dosis = dosis;
            }

            public int getInterval() {
                return interval;
            }

            public void setInterval(int interval) {
                this.interval = interval;
            }

            public Object getDays() {
                return days;
            }

            public void setDays(Object days) {
                this.days = days;
            }

            public String getLast_take() {
                return last_take;
            }

            public void setLast_take(String last_take) {
                this.last_take = last_take;
            }

            public MedicamentFormatBean getMedicament_format() {
                return medicament_format;
            }

            public void setMedicament_format(MedicamentFormatBean medicament_format) {
                this.medicament_format = medicament_format;
            }

            public MedicamentBean getMedicament() {
                return medicament;
            }

            public void setMedicament(MedicamentBean medicament) {
                this.medicament = medicament;
            }

            public List<SchedulesBean> getSchedules() {
                return schedules;
            }

            public void setSchedules(List<SchedulesBean> schedules) {
                this.schedules = schedules;
            }

            public static class MedicamentFormatBean {
                /**
                 * id : 3
                 * name : Cápsula
                 * img : /images/default-medicament.jpg
                 */

                private int id;
                private String name;
                private String img;

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

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class MedicamentBean {
                /**
                 * name : prograf 5 mg cápsula (Gador)
                 * grams : 0
                 * image :
                 * concept_id : 863221000167106
                 */
                @SerializedName("shortName")
                private String name;
                private int grams;
                private String image;
                private String concept_id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGrams() {
                    return grams;
                }

                public void setGrams(int grams) {
                    this.grams = grams;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getConcept_id() {
                    return concept_id;
                }

                public void setConcept_id(String concept_id) {
                    this.concept_id = concept_id;
                }
            }

            public static class SchedulesBean {
                /**
                 * id : 14
                 * prescription_id : 4
                 * type : 1
                 * day : null
                 * hour : 17:00:00
                 * dose : 1
                 * status : 1
                 * created_at : 2018-12-26 17:02:11
                 * updated_at : 2018-12-26 17:02:11
                 */

                private int id;
                private int prescription_id;
                private int type;
                private Integer day;
                private String hour;
                private String dose;
                private int status;
                private String created_at;
                private String updated_at;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPrescription_id() {
                    return prescription_id;
                }

                public void setPrescription_id(int prescription_id) {
                    this.prescription_id = prescription_id;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public Integer getDay() {
                    return day;
                }

                public void setDay(Integer day) {
                    this.day = day;
                }

                public String getHour() {
                    return hour;
                }

                public void setHour(String hour) {
                    this.hour = hour;
                }

                public String getDose() {
                    return dose;
                }

                public void setDose(String dose) {
                    this.dose = dose;
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
    }
}
