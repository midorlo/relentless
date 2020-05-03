package de.midorlo.relentless.ui.components;

import com.googlecode.gentyref.GenericTypeReflector;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementUtil;
import com.vaadin.flow.internal.ReflectTools;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.stream.Stream;

public abstract class Composite<T extends Component> extends Component {
    private T content;

    protected Composite() {
        super((Element)null);
    }

    protected T initContent() {
        return (T) ReflectTools.createInstance(findContentType(this.getClass()));
    }

    private static Class<? extends Component> findContentType(Class<? extends Composite> compositeClass) {
        Type type = GenericTypeReflector.getTypeParameter(compositeClass.getGenericSuperclass(), com.vaadin.flow.component.Composite.class.getTypeParameters()[0]);
        if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
            throw new IllegalStateException(getExceptionMessage(type));
        } else {
            return GenericTypeReflector.erase(type).asSubclass(Component.class);
        }
    }

    private static String getExceptionMessage(Type type) {
        if (type == null) {
            return "Composite is used as raw type: either add type information or override initContent().";
        } else {
            return type instanceof TypeVariable ? String.format("Could not determine the composite content type for TypeVariable '%s'. Either specify exact type or override initContent().", type.getTypeName()) : String.format("Could not determine the composite content type for %s. Override initContent().", type.getTypeName());
        }
    }

    public T getContent() {
        if (this.content == null) {
            T newContent = this.initContent();
            if (newContent == null) {
                throw new IllegalStateException("initContent returned null instead of a component");
            }

            this.setContent(newContent);
        }

        return this.content;
    }

    private void setContent(T content) {
        assert content.getElement().getComponent().isPresent() : "Composite should never be attached to an element which is not attached to a component";

        assert this.content == null : "Content has already been initialized";

        this.content = content;
        Element element = content.getElement();
        ElementUtil.setComponent(element, this);
    }

    public Element getElement() {
        return this.getContent().getElement();
    }

    public Stream<Component> getChildren() {
        return Stream.of(this.getContent());
    }
}
