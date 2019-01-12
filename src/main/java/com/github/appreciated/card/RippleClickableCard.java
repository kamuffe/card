package com.github.appreciated.card;

import com.github.appreciated.ripple.PaperRipple;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("clickable-card")
@HtmlImport("frontend://com/github/appreciated/card/clickable-card.html")
public class RippleClickableCard extends ClickableCard implements ClickNotifier {

    private final PaperRipple ripple;

    public RippleClickableCard() {
        this(null);
    }

    public RippleClickableCard(Component... components) {
        this(null, components);
    }

    public RippleClickableCard(ComponentEventListener listener, Component... components) {
        super(listener, components);
        ripple = new PaperRipple();
        ripple.setColor("var(--lumo-shade-30pct)");
        ripple.getElement().getStyle().set("margin", "unset");
        add(ripple);
    }

    public RippleClickableCard withElevation(int elevation) {
        super.setElevation(elevation);
        return this;
    }

    public RippleClickableCard withElevationOnActionEnabled(boolean enabled) {
        setElevationOnActionEnabled(enabled);
        return this;
    }

    public RippleClickableCard withRippleEnabled(boolean enable) {
        setRippleEnabled(enable);
        return this;
    }

    public void setRippleEnabled(boolean enable) {
        if (enable) {
            ripple.getElement().getStyle().remove("pointer-events");
        } else {
            ripple.getElement().getStyle().set("pointer-events", "none");
        }
    }

    public PaperRipple getRipple() {
        return ripple;
    }
}