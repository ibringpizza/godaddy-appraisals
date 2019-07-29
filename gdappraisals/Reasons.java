package gdappraisals;


public class Reasons
{
    private String sld;

    public String getSld ()
    {
        return sld;
    }

    public void setSld (String sld)
    {
        this.sld = sld;
    }
    
    private String domain;
    
    private String price;
    
    private String num_of_other_tlds_in_use;
    
    public String getNum_of_other_tlds_in_use ()
    {
        return num_of_other_tlds_in_use;
    }

    public void setNum_of_other_tlds_in_use (String num_of_other_tlds_in_use)
    {
        this.num_of_other_tlds_in_use = num_of_other_tlds_in_use;
    }
    
    private String keyword1_avg_sold_price;
    
    public String getKeyword1_avg_sold_price ()
    {
        return keyword1_avg_sold_price;
    }

    public void setKeyword1_avg_sold_price (String keyword1_avg_sold_price)
    {
        this.keyword1_avg_sold_price = keyword1_avg_sold_price;
    }
    
    private String keyword1;
    
    public String getKeyword1 ()
    {
        return keyword1;
    }

    public void setKeyword1 (String keyword1)
    {
        this.keyword1 = keyword1;
    }
    
    private String keyword2_avg_sold_price;
    
    public String getkeyword2_avg_sold_price ()
    {
        return keyword2_avg_sold_price;
    }

    public void setkeyword2_avg_sold_price (String keyword2_avg_sold_price)
    {
        this.keyword2_avg_sold_price = keyword2_avg_sold_price;
    }
    
    private String keyword2;
    
    public String getkeyword2 ()
    {
        return keyword2;
    }

    public void setkeyword2 (String keyword2)
    {
        this.keyword2 = keyword2;
    }
    
    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }
    
    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }
    
    private String keyword;

    private String avg_sold_price;

    private String type;

    public String getKeyword ()
    {
        return keyword;
    }

    public void setKeyword (String keyword)
    {
        this.keyword = keyword;
    }

    public String getAvg_sold_price ()
    {
        return avg_sold_price;
    }

    public void setAvg_sold_price (String avg_sold_price)
    {
        this.avg_sold_price = avg_sold_price;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "[keyword = "+keyword+", avg_sold_price = "+avg_sold_price+", type = "+type+"]";
    }
    
}
