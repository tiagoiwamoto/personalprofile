package mock;

import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.resteasy.reactive.common.util.CaseInsensitiveMap;
import org.jboss.resteasy.reactive.server.core.multipart.FormData;
import org.jboss.resteasy.reactive.server.multipart.FileItem;
import org.jboss.resteasy.reactive.server.multipart.FormValue;

import java.nio.file.Path;

public class AppFormValueImpl implements FormValue {
    private final String value;
    private final String fileName;
    private final CaseInsensitiveMap<String> headers;
    private final FormData.FileItemImpl fileItemImpl;
    private final String charset;

    AppFormValueImpl(String value, CaseInsensitiveMap<String> headers) {
        this.value = value;
        this.headers = headers;
        this.fileName = null;
        this.fileItemImpl = null;
        this.charset = null;
    }

    AppFormValueImpl(String value, String charset, CaseInsensitiveMap<String> headers) {
        this.value = value;
        this.charset = charset;
        this.headers = headers;
        this.fileName = null;
        this.fileItemImpl = null;
    }

    AppFormValueImpl(Path file, final String fileName, CaseInsensitiveMap<String> headers) {
        this.fileItemImpl = new FormData.FileItemImpl(file);
        this.headers = headers;
        this.fileName = fileName;
        this.value = null;
        this.charset = null;
    }

    AppFormValueImpl(byte[] data, String fileName, CaseInsensitiveMap<String> headers) {
        this.fileItemImpl = new FormData.FileItemImpl(data);
        this.fileName = fileName;
        this.headers = headers;
        this.value = null;
        this.charset = null;
    }

    public String getValue() {
        if (this.value == null) {
            throw new RuntimeException("Form value is a file");
        } else {
            return this.value;
        }
    }

    public String getCharset() {
        return this.charset;
    }

    public FormData.FileItemImpl getFileItem() {
        if (this.fileItemImpl == null) {
            throw new RuntimeException("Form value is a string");
        } else {
            return this.fileItemImpl;
        }
    }

    public boolean isFileItem() {
        return this.fileItemImpl != null;
    }

    public CaseInsensitiveMap<String> getHeaders() {
        return this.headers;
    }

    public String getFileName() {
        return this.fileName;
    }
}
