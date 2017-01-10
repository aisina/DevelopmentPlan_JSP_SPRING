package MVC_PROJECT.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by innopolis on 27.12.2016.
 */
public class PlanTypes {

    private List<String> plantypes = new ArrayList();


    public List<String> getPlantypes(){
        //http://1popersonalu.ru/info/vidy-attestacii-personala.html
        this.plantypes.add("Очередная аттестация");
        this.plantypes.add("Внеочередная");
        this.plantypes.add("Для продвижения по службе");
        this.plantypes.add("Повторная");
        this.plantypes.add("По истечении испытательного срока");

        return this.plantypes;
    }

}
