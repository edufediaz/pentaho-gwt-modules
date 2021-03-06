package org.pentaho.gwt.widgets.samples;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import org.pentaho.gwt.widgets.client.controls.ColorPicker;
import org.pentaho.gwt.widgets.client.controls.ColorPickerListener;
import org.pentaho.gwt.widgets.client.listbox.CustomListBox;
import org.pentaho.gwt.widgets.client.listbox.DefaultListItem;

/**
 * Created by IntelliJ IDEA.
 * User: Nick Baker
 * Date: Mar 9, 2009
 * Time: 12:54:29 PM
 */
@SuppressWarnings("nls")
public class SampleApp implements EntryPoint {
  @SuppressWarnings("deprecation")
  public void onModuleLoad() {

    final CustomListBox list = new CustomListBox();

    list.addItem("Alberta");
    list.addItem("Atlanta");
    list.addItem("San Francisco");
    list.addItem("Alberta");
    list.addItem("Atlanta");
    list.addItem("San Francisco");
    list.addItem("Alberta");
    list.addItem("Atlanta");
    list.addItem("San Francisco");
    list.addItem("Alberta");
    list.addItem("Atlanta");
    list.addItem("San Francisco");
    list.add("Alberta");
    list.add("Atlanta");
    list.add("San Francisco");
    list.add("Alberta");
    list.add("Atlanta");
    list.add("San Francisco");
    list.addItem(new DefaultListItem("Testing", new Image("16x16sample.png")));
    list.add(new DefaultListItem("Testing 2", new CheckBox()));

//    list.setVisibleRowCount(6);

    list.addChangeListener(new ChangeListener(){
      public void onChange(Widget widget) {
        System.out.println(""+list.getSelectedIndex());
      }
    });


    list.setWidth("100%");
    list.setHeight("100%");

    //RootPanel.get().add(new Label("Combo: "));
    //RootPanel.get().add(list);


    final CustomListBox list2 = new CustomListBox();

    list2.addItem("Alberta");
    list2.addItem("Atlanta");
    list2.addItem("San Francisco");
    list2.addItem("Alberta");
    list2.addItem("Atlanta");
    list2.addItem("San Francisco");
    list2.addItem("Alberta");
    list2.addItem("Atlanta");
    list2.addItem("San Francisco");
    list2.addItem("Alberta");
    list2.addItem("Atlanta");
    list2.addItem("San Francisco");
    list2.add("Alberta");
    list2.add("Atlanta");
    list2.add("San Francisco");
    list2.add("Alberta");
    list2.add("Atlanta");
    list2.add("San Francisco");
    list2.add(new DefaultListItem("Testing", new Image("16x16sample.png")));
    list2.addItem(new DefaultListItem("Testing 2", new CheckBox()));

    list2.setEditable(true);
    list2.setValue("Bogus");


    RootPanel.get().add(new Label(""));
    RootPanel.get().add(new Label("Combo2: "));
    RootPanel.get().add(list2);

    
    CustomListBox list3 = new CustomListBox();

    DefaultListItem dli = null;
    dli = new DefaultListItem("Testing 1", new Image("16x16sample.png"));
    dli.setValue("Value of Testing 1");
    list3.add(dli);
    
    dli = new DefaultListItem("Testing 2", new CheckBox());
    dli.setValue("Value of Testing 2");
    list3.add(dli);

    //RootPanel.get().add(new Label(""));
    //RootPanel.get().add(new Label("Combo3: "));
    //RootPanel.get().add(list3);

    Label showSelectedLabel = new Label("Selected item's value:");
    
    final TextBox showSelectedTextBox = new TextBox();
    showSelectedTextBox.setReadOnly(true);
    
    list3.addChangeListener(new ChangeListener(){

    public void onChange(Widget widget) {
      String val = (String)((CustomListBox)widget).getSelectedItem().getValue();
      if(val != null){
        showSelectedTextBox.setText(val);
      }
    }});
    final CustomListBox list4 = new CustomListBox();

    list4.addItem("Albert");
    list4.addItem("Greg");
    list4.setWidth("170px");

    RootPanel.get().add(new Label(""));
    RootPanel.get().add(new Label("Combo4: "));
    RootPanel.get().add(list4);

    RootPanel.get().add(showSelectedLabel);
    RootPanel.get().add(showSelectedTextBox);
    
    
    
    final ColorPicker picker = new ColorPicker();
    picker.addColorPickerListener(new ColorPickerListener(){

      public void colorPicked(ColorPicker picker) {
        System.out.println("color: " + picker.getColor());
        
      }
      
    });
    
    RootPanel.get().add(picker);
    Button btn = new Button("colorPicker");
    RootPanel.get().add(btn);
    
    btn.addClickHandler(new ClickHandler(){

      public void onClick(ClickEvent event) {
        picker.showPicker();
        
      }
      
    });
  }
}
