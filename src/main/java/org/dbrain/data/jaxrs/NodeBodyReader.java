package org.dbrain.data.jaxrs;

import org.dbrain.data.TextSerializer;
import org.dbrain.data.tree.Node;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Simple body writer that stream the element to the entity,
 */
@javax.ws.rs.ext.Provider
public class NodeBodyReader implements MessageBodyReader<Node> {

    final TextSerializer serializer;

    public NodeBodyReader(TextSerializer serializer ) {
        this.serializer = serializer;
    }

    /**
     * Return the charset from the mediatype. If none, default to UTF-8.
     */
    private static final Charset getCharset(MediaType m) {
        String name = (m == null) ? null : m.getParameters().get("charset");
        return (name == null) ? Charset.forName("UTF-8") : Charset.forName(name);
    }

    @Override
    public boolean isReadable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType ) {
        return Node.class.isAssignableFrom( type );
    }

    @Override
    public Node readFrom(Class<Node> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream ) throws IOException, WebApplicationException {
        InputStreamReader isr = new InputStreamReader( entityStream, getCharset( mediaType ) );
        return serializer.read( isr, Node.class );
    }

}
