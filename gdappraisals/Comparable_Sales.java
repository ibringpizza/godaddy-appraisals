package gdappraisals;


public class Comparable_Sales
{
    private String price;

    private String domain;

    private String year;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    @Override
    public String toString()
    {
        return domain + " -> " + price + " - " + year;
    }
}
