package chapter1.old;

import lombok.Getter;

@Getter
public class Audience
{
    private final Bag bag;
    
    public Audience(Bag bag)
    {
        this.bag = bag;
    }
}
