package gdappraisals;


class MainParser {
    private Comparable_Sales[] comparable_sales;

    private String status;

    private String govalue;

    private String[] govalue_limits;

    private String domain;

    private Reasons[] reasons;

    public Comparable_Sales[] getComparable_sales ()
    {
        return comparable_sales;
    }

    public void setComparable_sales (Comparable_Sales[] comparable_sales)
    {
        this.comparable_sales = comparable_sales;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getGovalue ()
    {
        return govalue;
    }

    public void setGovalue (String govalue)
    {
        this.govalue = govalue;
    }

    public String[] getGovalue_limits ()
    {
        return govalue_limits;
    }

    public void setGovalue_limits (String[] govalue_limits)
    {
        this.govalue_limits = govalue_limits;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

    public Reasons[] getReasons ()
    {
        return reasons;
    }

    public void setReasons (Reasons[] reasons)
    {
        this.reasons = reasons;
    }

    @Override
    public String toString()
    {
        return "[comparable_sales = "+comparable_sales+", status = "+status+", govalue = "+govalue+", govalue_limits = "+govalue_limits+", domain = "+domain+", reasons = "+reasons+"]";
    }
}
