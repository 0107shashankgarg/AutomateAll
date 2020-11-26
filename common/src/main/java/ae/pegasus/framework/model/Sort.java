package ae.pegasus.framework.model;

public class Sort {

    private SortDirection direction;
    private String property;
    private boolean ignoreCase;
    private String nullHandling;
    private boolean ascending;
    private boolean descending;

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public String getNullHandling() {
        return nullHandling;
    }

    public void setNullHandling(String nullHandling) {
        this.nullHandling = nullHandling;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }
}
