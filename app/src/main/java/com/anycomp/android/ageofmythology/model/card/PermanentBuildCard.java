package com.anycomp.android.ageofmythology.model.card;


import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentBuildCard extends PermanentActionCard {
    
    public PermanentBuildCard(Culture culture) {
         setName("Build");
        setCulture(culture);
        setImagePath(culture.getPermanentBuildCardImage());
    }


    @Override
    public void play() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Building");
        openBuildingPopup();
        
    }
    int i=0;
    private void openBuildingPopup() {
//        Nifty nifty = m.getNifty();
//        Screen screen = nifty.getCurrentScreen();
//        Element p = nifty.createPopupWithId("BuildingSelectionPopup", "bsp");
//        //Element panel = 
//        //nifty.showPopup()
//        Element topPanel = p.findElementByName("#tspm_panel_top");
//        Element middlePanel = p.findElementByName("#tspm_panel_middle");
//        Element bottomPanel = p.findElementByName("#tspm_panel_bottom");
//        Element current = topPanel;
//        
//        final ArrayList<Building> al = BuildingContainer.getInstance().getList();
//        for(i=0;i<al.size();i++) {
//           final Building t = al.get(i);
//            if(i==6) {
//                System.out.println("Change to middle paneel");
//                current = middlePanel;
//            } else if(i== 12) {
//                System.out.println("Change to botoe paneel");
//                current = bottomPanel;
//            }
//            System.out.println("b4 tile creating");
//            
//              new ImageBuilder("tile"+i) {{
//                 
//                width("80px");
//
//                 height("80px");
//
//                filename(al.get(i).getImagePath());
//                interactOnClick("pick("+i+")");
//                }}.build(nifty, screen, current);
//            }
//        nifty.showPopup(nifty.getCurrentScreen(), p.getId(), null);
        
    }

}
