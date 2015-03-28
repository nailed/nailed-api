package jk_5.nailed.api.text;

import com.google.common.collect.UnmodifiableIterator;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a recursive {@link Iterator} for {@link Text} including the text
 * itself as well as all children texts.
 */
class TextIterator extends UnmodifiableIterator<Text> {

    private final Text text;
    @Nullable private Iterator<Text> children;
    @Nullable private Iterator<Text> currentChildIterator;

    /**
     * Constructs a new {@link TextIterator} for the specified {@link Text}.
     *
     * @param text The root text for the iterator
     */
    TextIterator(Text text) {
        this.text = text;
    }

    @Override
    public boolean hasNext() {
        return this.children == null || (this.currentChildIterator != null && this.currentChildIterator.hasNext()) || this.children.hasNext();
    }

    @Override
    public Text next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.children == null) {
            this.children = this.text.children.iterator();
            return this.text;
        } else if (this.currentChildIterator == null || !this.currentChildIterator.hasNext()) {
            this.currentChildIterator = this.children.next().childrenIterable.iterator();
        }

        return this.currentChildIterator.next();
    }

}
