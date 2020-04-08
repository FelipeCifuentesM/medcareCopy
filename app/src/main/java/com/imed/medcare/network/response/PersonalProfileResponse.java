package com.imed.medcare.network.response;

import java.util.List;

public class PersonalProfileResponse {


    /**
     * status : success
     * message : ok
     * resource : record
     * code : 200
     * links : {}
     * data : [{"item":{"id":1,"name":"Grupo sanguíneo y Rh","current_value":[],"current_name":null,"key":"blood_type","type":2,"options":[{"id":1,"name":"O positivo","status":1,"created_at":null,"updated_at":null},{"id":2,"name":"A negativo","status":1,"created_at":null,"updated_at":null},{"id":3,"name":"A positivo","status":1,"created_at":null,"updated_at":null},{"id":4,"name":"B negativo","status":1,"created_at":null,"updated_at":null},{"id":5,"name":"B positivo","status":1,"created_at":null,"updated_at":null},{"id":6,"name":"AB negativo","status":1,"created_at":null,"updated_at":null},{"id":7,"name":"AB positivo","status":1,"created_at":null,"updated_at":null}]}},{"item":{"id":2,"name":"Tranfusiones","current_value":[],"current_name":null,"key":"transfusions","type":2,"options":[{"id":5,"name":"si","type":2},{"id":6,"name":"no","type":2}]}},{"item":{"id":3,"name":"Abuso sicológico","current_value":[],"current_name":null,"key":"psyco","type":2,"options":[{"id":5,"name":"si","type":2},{"id":6,"name":"no","type":2}]}},{"item":{"id":4,"name":"Abuso físico","current_value":[],"current_name":null,"type":2,"key":"physical","options":[{"id":5,"name":"si","type":2},{"id":6,"name":"no","type":2}]}},{"item":{"id":5,"name":"Vacuna influenza","current_value":[],"current_name":null,"type":2,"key":"annual_vaccine","options":[{"id":5,"name":"si","type":2},{"id":6,"name":"no","type":2}]}},{"item":{"id":6,"name":"Enfermedades Crónicas","current_value":[],"current_name":"","type":3,"key":"chronic_diseases","options":[{"id":1,"name":"Hipertensión arterial","status":1,"created_at":null,"updated_at":null},{"id":2,"name":"Diabetes tipo 1 o tipo 2","status":1,"created_at":null,"updated_at":null},{"id":3,"name":"Tuberculosis","status":1,"created_at":null,"updated_at":null},{"id":4,"name":"Cáncer","status":1,"created_at":null,"updated_at":null},{"id":5,"name":"Obesidad","status":1,"created_at":null,"updated_at":null},{"id":6,"name":"Desnutrición","status":1,"created_at":null,"updated_at":null},{"id":7,"name":"Hepatitis A","status":1,"created_at":null,"updated_at":null},{"id":8,"name":"Hepatitis B","status":1,"created_at":null,"updated_at":null},{"id":9,"name":"Hepatitis C","status":1,"created_at":null,"updated_at":null},{"id":10,"name":"Infección por virus SIDA","status":1,"created_at":null,"updated_at":null},{"id":11,"name":"Sífilis","status":1,"created_at":null,"updated_at":null},{"id":12,"name":"Enfermedad neurológica, epilepsia u otras","status":1,"created_at":null,"updated_at":null},{"id":13,"name":"Enfermedad psiquiátrica","status":1,"created_at":null,"updated_at":null},{"id":14,"name":"Dolor de cabeza frecuente","status":1,"created_at":null,"updated_at":null},{"id":15,"name":"Enfermedad cardáaca","status":1,"created_at":null,"updated_at":null},{"id":16,"name":"Enfermedad pulmonar","status":1,"created_at":null,"updated_at":null},{"id":17,"name":"Enfermedad digestiva","status":1,"created_at":null,"updated_at":null},{"id":18,"name":"Enfermedad Hepática","status":1,"created_at":null,"updated_at":null},{"id":19,"name":"Enfermedad al pancreas","status":1,"created_at":null,"updated_at":null},{"id":20,"name":"Enfermedad renal","status":1,"created_at":null,"updated_at":null},{"id":21,"name":"Enfermedad Urológica","status":1,"created_at":null,"updated_at":null},{"id":22,"name":"Enfermedad de las articulaciones","status":1,"created_at":null,"updated_at":null},{"id":23,"name":"Enfermedad Ósea","status":1,"created_at":null,"updated_at":null},{"id":24,"name":"Enfermedad ginecológica","status":1,"created_at":null,"updated_at":null},{"id":25,"name":"Enfermedad ocular (visión general o ceguera)","status":1,"created_at":null,"updated_at":null},{"id":26,"name":"Enfermedad auditiva (hipoacusia, sordera)","status":1,"created_at":null,"updated_at":null},{"id":27,"name":"Traumatismo grave","status":1,"created_at":null,"updated_at":null},{"id":28,"name":"Ouemadura grave","status":1,"created_at":null,"updated_at":null},{"id":29,"name":"Sangramiento (al toser, en deposiciones, ginecológico. etc)","status":1,"created_at":null,"updated_at":null},{"id":30,"name":"Infección pulmonar","status":1,"created_at":null,"updated_at":null},{"id":31,"name":"Infección renal o de la via urinaria","status":1,"created_at":null,"updated_at":null},{"id":32,"name":"Infección  Intestinal","status":1,"created_at":null,"updated_at":null},{"id":33,"name":"Infección a la piel","status":1,"created_at":null,"updated_at":null},{"id":34,"name":"Alergia en la piel","status":1,"created_at":null,"updated_at":null},{"id":35,"name":"Alergia a medicamentos","status":1,"created_at":null,"updated_at":null},{"id":36,"name":"Otras alergias","status":1,"created_at":null,"updated_at":null},{"id":37,"name":"Accidentes graves con hospitalización","status":1,"created_at":null,"updated_at":null},{"id":38,"name":"Operación al esófago","status":1,"created_at":null,"updated_at":null},{"id":39,"name":"Operación al estómago","status":1,"created_at":null,"updated_at":null},{"id":40,"name":"Operación de vesícula","status":1,"created_at":null,"updated_at":null},{"id":41,"name":"Operación al hígado","status":1,"created_at":null,"updated_at":null},{"id":42,"name":"Operación al páncreas","status":1,"created_at":null,"updated_at":null},{"id":43,"name":"Operación al apéndice","status":1,"created_at":null,"updated_at":null},{"id":44,"name":"Operación al intestino","status":1,"created_at":null,"updated_at":null},{"id":45,"name":"Operación en el  abdomen con otro diagnóstico","status":1,"created_at":null,"updated_at":null},{"id":46,"name":"Operación pulmonar","status":1,"created_at":null,"updated_at":null},{"id":47,"name":"Operación al corazón","status":1,"created_at":null,"updated_at":null},{"id":48,"name":"Operación al vascular (aorta, várices, otras)","status":1,"created_at":null,"updated_at":null},{"id":49,"name":"Operación al riñón","status":1,"created_at":null,"updated_at":null},{"id":50,"name":"Operación de la via urinaria y vejiga","status":1,"created_at":null,"updated_at":null},{"id":51,"name":"Operación de la próstata","status":1,"created_at":null,"updated_at":null},{"id":52,"name":"Operación ginecológica","status":1,"created_at":null,"updated_at":null},{"id":53,"name":"Operación traumotológica","status":1,"created_at":null,"updated_at":null},{"id":54,"name":"Operación al cerebro","status":1,"created_at":null,"updated_at":null},{"id":55,"name":"Operación en el cuello (tiroides, carótidas u otras)","status":1,"created_at":null,"updated_at":null},{"id":56,"name":"Operación en la piel","status":1,"created_at":null,"updated_at":null},{"id":57,"name":"Cesárea","status":1,"created_at":null,"updated_at":null},{"id":58,"name":"Operado transplante renal","status":1,"created_at":null,"updated_at":null},{"id":59,"name":"Operado transplante páncreas","status":1,"created_at":null,"updated_at":null},{"id":60,"name":"Operado transplante páncreas y riñon","status":1,"created_at":null,"updated_at":null},{"id":61,"name":"Operado transplante higado","status":1,"created_at":null,"updated_at":null},{"id":62,"name":"Operado transplante corazón","status":1,"created_at":null,"updated_at":null},{"id":63,"name":"Operado transplante pulmón","status":1,"created_at":null,"updated_at":null},{"id":64,"name":"Operado transplante intestino","status":1,"created_at":null,"updated_at":null},{"id":65,"name":"Transplante de piel","status":1,"created_at":null,"updated_at":null},{"id":66,"name":"Transplante de córnea","status":1,"created_at":null,"updated_at":null},{"id":67,"name":"Tratamiento dental. caries u otras","status":1,"created_at":null,"updated_at":null},{"id":68,"name":"Prótesis (articulares, dentales, vasculares u otras)","status":1,"created_at":null,"updated_at":null},{"id":69,"name":"Marcapaso (cardíaco)","status":1,"created_at":null,"updated_at":null},{"id":70,"name":"Transfusiones por anemia","status":1,"created_at":null,"updated_at":null},{"id":71,"name":"Discapacidad ambulatoria, uso de aparatos","status":1,"created_at":null,"updated_at":null},{"id":72,"name":"Discapacidad visual, miopia, ceguera","status":1,"created_at":null,"updated_at":null},{"id":73,"name":"Discapacidad auditiva, sordera","status":1,"created_at":null,"updated_at":null},{"id":74,"name":"Enfermedades genéticas (Down.Asperger.Otros)","status":1,"created_at":null,"updated_at":null}]}},{"item":{"id":7,"name":"Alergias","current_value":[3,5,11],"current_name":" alimento - leche alimentos - frutos secos Fármacos -  vitaminas.","type":3,"key":"allergies","options":[{"id":1,"name":"Alergia en la piel","status":0,"created_at":null,"updated_at":null},{"id":2,"name":"Alergia a medicamentos","status":0,"created_at":null,"updated_at":null},{"id":3,"name":"alimento - leche","status":1,"created_at":null,"updated_at":null},{"id":4,"name":"alimento - huevos","status":1,"created_at":null,"updated_at":null},{"id":5,"name":"alimentos - frutos secos","status":1,"created_at":null,"updated_at":null},{"id":6,"name":"Fármacos -  antibióticos","status":1,"created_at":null,"updated_at":null},{"id":7,"name":"Fármacos -  analgésicos (AINE)","status":1,"created_at":null,"updated_at":null},{"id":8,"name":"Fármacos -  analgésicos locales o generales","status":1,"created_at":null,"updated_at":null},{"id":9,"name":"Fármacos -  relajantes musculares.","status":1,"created_at":null,"updated_at":null},{"id":10,"name":"Fármacos -  medios de contraste iodados.","status":1,"created_at":null,"updated_at":null},{"id":11,"name":"Fármacos -  vitaminas.","status":1,"created_at":null,"updated_at":null},{"id":12,"name":"Fármacos -  penicilina.","status":1,"created_at":null,"updated_at":null},{"id":13,"name":"Alergia a medicamentos","status":1,"created_at":null,"updated_at":null}]}},{"item":{"id":8,"name":"Morbilidades previas","current_value":[],"current_name":"","type":3,"key":"previous_problems","options":[{"id":1,"name":"Hipertensión arterial","status":1,"created_at":null,"updated_at":null},{"id":2,"name":"Diabetes tipo 1 o tipo 2","status":1,"created_at":null,"updated_at":null},{"id":3,"name":"Tuberculosis","status":1,"created_at":null,"updated_at":null},{"id":4,"name":"Cáncer","status":1,"created_at":null,"updated_at":null},{"id":5,"name":"Obesidad","status":1,"created_at":null,"updated_at":null},{"id":6,"name":"Desnutrición","status":1,"created_at":null,"updated_at":null},{"id":7,"name":"Hepatitis A","status":1,"created_at":null,"updated_at":null},{"id":8,"name":"Hepatitis B","status":1,"created_at":null,"updated_at":null},{"id":9,"name":"Hepatitis C","status":1,"created_at":null,"updated_at":null},{"id":10,"name":"Infección por virus SIDA","status":1,"created_at":null,"updated_at":null},{"id":11,"name":"Sífilis","status":1,"created_at":null,"updated_at":null},{"id":12,"name":"Enfermedad neurológica, epilepsia u otras","status":1,"created_at":null,"updated_at":null},{"id":13,"name":"Enfermedad psiquiátrica","status":1,"created_at":null,"updated_at":null},{"id":14,"name":"Dolor de cabeza frecuente","status":1,"created_at":null,"updated_at":null},{"id":15,"name":"Enfermedad cardáaca","status":1,"created_at":null,"updated_at":null},{"id":16,"name":"Enfermedad pulmonar","status":1,"created_at":null,"updated_at":null},{"id":17,"name":"Enfermedad digestiva","status":1,"created_at":null,"updated_at":null},{"id":18,"name":"Enfermedad Hepática","status":1,"created_at":null,"updated_at":null},{"id":19,"name":"Enfermedad al pancreas","status":1,"created_at":null,"updated_at":null},{"id":20,"name":"Enfermedad renal","status":1,"created_at":null,"updated_at":null},{"id":21,"name":"Enfermedad Urológica","status":1,"created_at":null,"updated_at":null},{"id":22,"name":"Enfermedad de las articulaciones","status":1,"created_at":null,"updated_at":null},{"id":23,"name":"Enfermedad Ósea","status":1,"created_at":null,"updated_at":null},{"id":24,"name":"Enfermedad ginecológica","status":1,"created_at":null,"updated_at":null},{"id":25,"name":"Enfermedad ocular (visión general o ceguera)","status":1,"created_at":null,"updated_at":null},{"id":26,"name":"Enfermedad auditiva (hipoacusia, sordera)","status":1,"created_at":null,"updated_at":null},{"id":27,"name":"Traumatismo grave","status":1,"created_at":null,"updated_at":null},{"id":28,"name":"Ouemadura grave","status":1,"created_at":null,"updated_at":null},{"id":29,"name":"Sangramiento (al toser, en deposiciones, ginecológico. etc)","status":1,"created_at":null,"updated_at":null},{"id":30,"name":"Infección pulmonar","status":1,"created_at":null,"updated_at":null},{"id":31,"name":"Infección renal o de la via urinaria","status":1,"created_at":null,"updated_at":null},{"id":32,"name":"Infección  Intestinal","status":1,"created_at":null,"updated_at":null},{"id":33,"name":"Infección a la piel","status":1,"created_at":null,"updated_at":null},{"id":34,"name":"Alergia en la piel","status":1,"created_at":null,"updated_at":null},{"id":35,"name":"Alergia a medicamentos","status":1,"created_at":null,"updated_at":null},{"id":36,"name":"Otras alergias","status":1,"created_at":null,"updated_at":null},{"id":37,"name":"Accidentes graves con hospitalización","status":1,"created_at":null,"updated_at":null},{"id":38,"name":"Operación al esófago","status":1,"created_at":null,"updated_at":null},{"id":39,"name":"Operación al estómago","status":1,"created_at":null,"updated_at":null},{"id":40,"name":"Operación de vesícula","status":1,"created_at":null,"updated_at":null},{"id":41,"name":"Operación al hígado","status":1,"created_at":null,"updated_at":null},{"id":42,"name":"Operación al páncreas","status":1,"created_at":null,"updated_at":null},{"id":43,"name":"Operación al apéndice","status":1,"created_at":null,"updated_at":null},{"id":44,"name":"Operación al intestino","status":1,"created_at":null,"updated_at":null},{"id":45,"name":"Operación en el  abdomen con otro diagnóstico","status":1,"created_at":null,"updated_at":null},{"id":46,"name":"Operación pulmonar","status":1,"created_at":null,"updated_at":null},{"id":47,"name":"Operación al corazón","status":1,"created_at":null,"updated_at":null},{"id":48,"name":"Operación al vascular (aorta, várices, otras)","status":1,"created_at":null,"updated_at":null},{"id":49,"name":"Operación al riñón","status":1,"created_at":null,"updated_at":null},{"id":50,"name":"Operación de la via urinaria y vejiga","status":1,"created_at":null,"updated_at":null},{"id":51,"name":"Operación de la próstata","status":1,"created_at":null,"updated_at":null},{"id":52,"name":"Operación ginecológica","status":1,"created_at":null,"updated_at":null},{"id":53,"name":"Operación traumotológica","status":1,"created_at":null,"updated_at":null},{"id":54,"name":"Operación al cerebro","status":1,"created_at":null,"updated_at":null},{"id":55,"name":"Operación en el cuello (tiroides, carótidas u otras)","status":1,"created_at":null,"updated_at":null},{"id":56,"name":"Operación en la piel","status":1,"created_at":null,"updated_at":null},{"id":57,"name":"Cesárea","status":1,"created_at":null,"updated_at":null},{"id":58,"name":"Operado transplante renal","status":1,"created_at":null,"updated_at":null},{"id":59,"name":"Operado transplante páncreas","status":1,"created_at":null,"updated_at":null},{"id":60,"name":"Operado transplante páncreas y riñon","status":1,"created_at":null,"updated_at":null},{"id":61,"name":"Operado transplante higado","status":1,"created_at":null,"updated_at":null},{"id":62,"name":"Operado transplante corazón","status":1,"created_at":null,"updated_at":null},{"id":63,"name":"Operado transplante pulmón","status":1,"created_at":null,"updated_at":null},{"id":64,"name":"Operado transplante intestino","status":1,"created_at":null,"updated_at":null},{"id":65,"name":"Transplante de piel","status":1,"created_at":null,"updated_at":null},{"id":66,"name":"Transplante de córnea","status":1,"created_at":null,"updated_at":null},{"id":67,"name":"Tratamiento dental. caries u otras","status":1,"created_at":null,"updated_at":null},{"id":68,"name":"Prótesis (articulares, dentales, vasculares u otras)","status":1,"created_at":null,"updated_at":null},{"id":69,"name":"Marcapaso (cardíaco)","status":1,"created_at":null,"updated_at":null},{"id":70,"name":"Transfusiones por anemia","status":1,"created_at":null,"updated_at":null},{"id":71,"name":"Discapacidad ambulatoria, uso de aparatos","status":1,"created_at":null,"updated_at":null},{"id":72,"name":"Discapacidad visual, miopia, ceguera","status":1,"created_at":null,"updated_at":null},{"id":73,"name":"Discapacidad auditiva, sordera","status":1,"created_at":null,"updated_at":null},{"id":74,"name":"Enfermedades genéticas (Down.Asperger.Otros)","status":1,"created_at":null,"updated_at":null}]}}]
     */

    private String status;
    private String message;
    private String resource;
    private int code;
    private LinksBean links;
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
         * item : {"id":1,"name":"Grupo sanguíneo y Rh","current_value":[],"current_name":null,"key":"blood_type","type":2,"options":[{"id":1,"name":"O positivo","status":1,"created_at":null,"updated_at":null},{"id":2,"name":"A negativo","status":1,"created_at":null,"updated_at":null},{"id":3,"name":"A positivo","status":1,"created_at":null,"updated_at":null},{"id":4,"name":"B negativo","status":1,"created_at":null,"updated_at":null},{"id":5,"name":"B positivo","status":1,"created_at":null,"updated_at":null},{"id":6,"name":"AB negativo","status":1,"created_at":null,"updated_at":null},{"id":7,"name":"AB positivo","status":1,"created_at":null,"updated_at":null}]}
         */

        private ItemBean item;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * id : 1
             * name : Grupo sanguíneo y Rh
             * current_value : []
             * current_name : null
             * key : blood_type
             * type : 2
             * options : [{"id":1,"name":"O positivo","status":1,"created_at":null,"updated_at":null},{"id":2,"name":"A negativo","status":1,"created_at":null,"updated_at":null},{"id":3,"name":"A positivo","status":1,"created_at":null,"updated_at":null},{"id":4,"name":"B negativo","status":1,"created_at":null,"updated_at":null},{"id":5,"name":"B positivo","status":1,"created_at":null,"updated_at":null},{"id":6,"name":"AB negativo","status":1,"created_at":null,"updated_at":null},{"id":7,"name":"AB positivo","status":1,"created_at":null,"updated_at":null}]
             */

            private int id;
            private String name;
            private String current_name;
            private String key;
            private int type;
            private List<Integer> current_value;
            private List<OptionsBean> options;

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

            public String getCurrent_name() {
                return current_name;
            }

            public void setCurrent_name(String current_name) {
                this.current_name = current_name;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<Integer> getCurrent_value() {
                return current_value;
            }

            public void setCurrent_value(List<Integer> current_value) {
                this.current_value = current_value;
            }

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
                this.options = options;
            }

            public static class OptionsBean {
                /**
                 * id : 1
                 * name : O positivo
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
