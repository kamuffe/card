package com.github.appreciated.card;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class StatefulCardGroup<T extends StatefulCard> extends Composite<VerticalLayout> {
    private StatefulCard currentFocus;
    private Consumer<Optional<T>> listener;
    private List<T> cards = new ArrayList<>();

    public StatefulCardGroup(T... cards) {
        add(cards);
        getContent().setMargin(false);
        getContent().setPadding(false);
        getContent().setSpacing(false);
    }

    public void add(T... cards) {
        getContent().add(cards);
        getContent().add(cards);
        Arrays.stream(cards).forEach(card -> card.addClickListener(event -> setState(card)));
        this.cards.addAll(Arrays.asList(cards));
    }

    /**
     * @param nextFocus The next card to be focused, may be null
     */
    public void setState(T nextFocus) {
        setState(nextFocus, true);
    }

    public StatefulCard getState() {
        return currentFocus;
    }

    public void setState(T nextFocus, boolean notifyListeners) {
        if (currentFocus != nextFocus) {
            if (nextFocus != null) {
                nextFocus.setSelected(true);
            }
            if (currentFocus != null) {
                currentFocus.setSelected(false);
            }
            currentFocus = nextFocus;
            if (listener != null && notifyListeners) {
                listener.accept(Optional.ofNullable(nextFocus));
            }
        }
    }

    public StatefulCardGroup<T> withStateChangedListener(Consumer<Optional<T>> listener) {
        setStateChangedListener(listener);
        return this;
    }

    public void setStateChangedListener(Consumer<Optional<T>> listener) {
        this.listener = listener;
    }

    public void setHighlight(boolean enabled) {
        if (enabled) {
            getContent().getStyle().remove("--card-state-highlight");
        } else {
            getContent().getStyle().set("--card-state-highlight", "transparent");
        }
    }

    public List<T> getCards() {
        return cards;
    }
}