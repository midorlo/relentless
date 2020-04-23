package de.midorlo.relentless.domain.item;

public enum Element {
    Frost,
    Blaze,
    Shock,
    Terra,
    Umbral,
    Radiant,
    Neutral,
    Unmachting; //Cheaty way to never match the Element

    /**
     * Compares 2 Elements for one attacking the other.
     * @param my attacker
     * @param his defender
     * @return positive = strong, negative = weak, 0 = regular
     */
    public static int compareForAttack(Element my, Element his) {
        return  (my.equals(his))
                ? -1
                : (Element.isOpposite(my, his) ? 1 : 0);
    }

    public static boolean isOpposite(Element my, Element his) {
        return his.equals(getOpposite(my));
    }

    public static Element getOpposite(Element element) {

        Element oppositeElement;
        //@formatter:off
        switch (element) {
            case Frost  : oppositeElement = Blaze  ; break;
            case Blaze  : oppositeElement = Frost  ; break;
            case Shock  : oppositeElement = Terra  ; break;
            case Terra  : oppositeElement = Shock  ; break;
            case Umbral : oppositeElement = Radiant; break;
            case Radiant: oppositeElement = Umbral ; break;
            default     : oppositeElement = Unmachting;
        }
        //@formatter:on
        return oppositeElement;
    }
}

