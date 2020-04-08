package com.imed.medcare.network.response;

public class DocumentSaveResponse {

    /**
     * status : ok
     * message : Obtenido con éxito
     * resource : docs
     * links : {}
     * page : null
     * total : 1
     * per_page : null
     * data : {"name":"hola","extension":"jpg","path":"e2f0bbd7ec009bc954ec90c88cd636c8_hola.jpg","description":"IMG__20180920_153105.jpg","updated_at":"2018-10-03 14:57:13","created_at":"2018-10-03 14:57:13","id":9}
     */

    private String status;
    private String message;
    private String resource;
    private LinksBean links;
    private Object page;
    private int total;
    private Object per_page;
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
         * name : hola
         * extension : jpg
         * path : e2f0bbd7ec009bc954ec90c88cd636c8_hola.jpg
         * description : IMG__20180920_153105.jpg
         * updated_at : 2018-10-03 14:57:13
         * created_at : 2018-10-03 14:57:13
         * id : 9
         */

        private String name;
        private String extension;
        private String path;
        private String description;
        private String updated_at;
        private String created_at;
        private int id;

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
