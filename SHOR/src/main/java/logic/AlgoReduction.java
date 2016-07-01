package logic;

import db.UrlManip;

public class AlgoReduction {

    private UrlManip db =new UrlManip(); //создаем обєкт для проврки дубликаторов которотких ссилок
    private String longurl ="";//эта переменная хранить длинную ссылку ведденую пользователем

    public AlgoReduction(String s)
    {
        String www="https";
        int c=0;
        for (int i=0;i<www.length();i++)
            if(s.charAt(i)==www.charAt(i)) // если ссылка уже содержыт префикс ничего не делать, в другом случаи добавить http
                c++;
        if (c<4)
            s="http://"+s;
        this.longurl =s;
    }
    public String doShort() {
        String symbols="0123456789_qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String tinyurl="";
        int tinylength = 5; //длина короткой ссылки
        int count = 0; // счетчик попыток
        if(db.getTinyurl(longurl)!=null) //Если такой юрл уже использовался тогда вернуть уже короткую ссылку
            return db.getTinyurl(longurl);
        //Генерировать юрл до тех пор пока он не будет уникальным
        while (true) {
            for (int i = 0; i < tinylength; i++)
                tinyurl = tinyurl+ symbols.charAt((int) (Math.random() * symbols.length() + 0));
            if (db.getLongurl(tinyurl)==null) //проверка на наличия
            {
                db.addLink(longurl,tinyurl);
                break;
            }
            tinyurl=""; //переинициализировать

            count++; // в противном случаи добавить счетчик попыток +1
            //Если не удалось сгенерировать уникальный короткий юрл 5 раз подряд, значит добавить к короткому юрл +1 символ
            if (count == tinylength)
                tinylength++;
        }
        return tinyurl;
    }
}
