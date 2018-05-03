package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/14.
 */

public class UploadBean {


    /**
     * code : 200
     * message : 操作成功
     * data : {"file":{"filepath":"http://leifeng.jzbwlkj.com/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg","name":"magazine-unlock-04-2.3.968-_b46866048a1047f1955390185aa3323b.jpg","id":null,"preview_url":"/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg","url":"http://leifeng.jzbwlkj.com/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg"}}
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
         * file : {"filepath":"http://leifeng.jzbwlkj.com/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg","name":"magazine-unlock-04-2.3.968-_b46866048a1047f1955390185aa3323b.jpg","id":null,"preview_url":"/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg","url":"http://leifeng.jzbwlkj.com/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg"}
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
             * filepath : http://leifeng.jzbwlkj.com/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg
             * name : magazine-unlock-04-2.3.968-_b46866048a1047f1955390185aa3323b.jpg
             * id : null
             * preview_url : /upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg
             * url : http://leifeng.jzbwlkj.com/upload/default/20180503/460d0ca8237a034e11f248346b6eedba.jpg
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
