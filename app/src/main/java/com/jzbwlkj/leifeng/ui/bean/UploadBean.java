package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/14.
 */

public class UploadBean {

    /**
     * code : 200
     * message : 操作成功
     * data : {"file":{"filepath":"default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg","name":"file.jpg","id":null,"preview_url":"/upload/default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg","url":"/upload/default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg"}}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * file : {"filepath":"default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg","name":"file.jpg","id":null,"preview_url":"/upload/default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg","url":"/upload/default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg"}
         */

        private FileBean file;

        public FileBean getFile() {
            return file;
        }

        public void setFile(FileBean file) {
            this.file = file;
        }

        public static class FileBean {
            /**
             * filepath : default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg
             * name : file.jpg
             * id : null
             * preview_url : /upload/default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg
             * url : /upload/default/20180414/cfa21863b8b821752f650004d7a1d31c.jpg
             */

            private String filepath;
            private String name;
            private Object id;
            private String preview_url;
            private String url;

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getPreview_url() {
                return preview_url;
            }

            public void setPreview_url(String preview_url) {
                this.preview_url = preview_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
