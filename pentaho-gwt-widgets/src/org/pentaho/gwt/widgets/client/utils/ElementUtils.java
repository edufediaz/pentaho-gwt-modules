/*
 * This program is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software 
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this 
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html 
 * or from the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright 2008 Pentaho Corporation.  All rights reserved.
 */
package org.pentaho.gwt.widgets.client.utils;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.google.gwt.user.client.ui.Widget;

public class ElementUtils {

  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  public static final int TOP = 0;
  public static final int BOTTOM = 1;
  
  
  public static native void blur(Element e)/*-{
   e.blur();
  }-*/;

  public static void removeScrollingFromSplitPane(Widget panel){
    if(!panel.isAttached()){
      //throw new IllegalStateException("Operation not allowed while element not on DOM");
    }
    
    if((panel instanceof HorizontalSplitPanel || panel instanceof VerticalSplitPanel) == false){
      throw new IllegalArgumentException("Widget not expected SplitPane type");
    }
    
    if(panel instanceof HorizontalSplitPanel){
      HorizontalSplitPanel hp = (HorizontalSplitPanel) panel;
      removeScrollingFromUpTo(hp.getLeftWidget().getElement(), hp.getElement());
      removeScrollingFromUpTo(hp.getRightWidget().getElement(), hp.getElement());
    } else {
      VerticalSplitPanel vp = (VerticalSplitPanel) panel;
      removeScrollingFromUpTo(vp.getTopWidget().getElement(), vp.getElement());
      removeScrollingFromUpTo(vp.getBottomWidget().getElement(), vp.getElement());
    }
    
  }
  
  public static void removeScrollingFromUpTo(Element bottom, Element top){
    
    Element ele = bottom;
    while(ele != top && ele.getParentElement() != null){
      ele.getStyle().setProperty("overflow", "visible");
      ele.getStyle().setProperty("overflowX", "visible");
      ele.getStyle().setProperty("overflowY", "visible");
      ele = ele.getParentElement();
    }
  }
  
  private static void killAutoScrolling(Element ele){
    ele.getStyle().setProperty("overflow", "visible");
    if(ele.hasChildNodes()){
      
      NodeList<Node> nodes = ele.getChildNodes();
      for(int i=0; i<nodes.getLength(); i++){
        Node n = nodes.getItem(i);
        if(n instanceof Element){
          killAutoScrolling((Element) n);
        }
      }
    }
  }
  

  public static native void preventTextSelection(Element ele) /*-{
    if(document.all){
      ele.onselectstart=function() {return false};
    } else {
      ele.style.MozUserSelect='none';
    }
  }-*/;

  

  public static native void toggleEmbedVisibility(boolean visible)/*-{

    var iframes = $doc.getElementsByTagName("iframe");
    for(var i=0; i<iframes.length; i++){
      var doc = (iframes[i].contentWindow.document || iframes[i].contentDocument);
      
      if(doc == null)
      {
        //IE7 you're ok anyway
        return;
      }
      
      var embeds = doc.getElementsByTagName("embed");
      for(var y=0; y<embeds.length; y++){
        if(visible){
          iframes[i].style.display = "" ;
          iframes[i].contentWindow.location.href = iframes[i].contentWindow.location.href
        } else {
          iframes[i].style.display = "none" ;
        }
      }
      
    }
    
  }-*/;

  
  public static native void convertPNGs() /*-{
    try{
      var arVersion = navigator.appVersion.split("MSIE")
      var version = parseFloat(arVersion[1])
    
      if ((version >= 5.5) && ($doc.body.filters)) 
      {
         for(var i=0; i<$doc.images.length; i++)
         {
            var img = $doc.images[i]
            var imgName = img.src.toUpperCase()
            if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
            {
               var imgID = (img.id) ? "id='" + img.id + "' " : ""
               var imgClass = (img.className) ? "class='" + img.className + "' " : ""
               var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
               var imgStyle = "display:inline-block;" + img.style.cssText 
               if (img.align == "left") imgStyle = "float:left;" + imgStyle
               if (img.align == "right") imgStyle = "float:right;" + imgStyle
               if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
               var strNewHTML = "<span " + imgID + imgClass + imgTitle
               + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
               + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
               + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
               img.outerHTML = strNewHTML
               i = i-1
            }
         }
      }
    } catch(e){
      //NON-IE
    }
  }-*/;
  
  
  public static native int[] calculateScrollOffsets(Element e)/*-{
    var x=0;
    var y=0;
    while(e.offsetParent != null){
      x += e.scrollLeft;
      y += e.scrollTop;
      e = e.offsetParent;
    }
    return [x,y];
  }-*/;
  

  public static native int[] calculateOffsets(Element e)/*-{
    var x=0;
    var y=0;
    while(e.offsetParent != null){
      x += e.offsetLeft;
      y += e.offsetTop;
      e = e.offsetParent;
    }
    return [x,y];
  }-*/;

  public static Element findElementAboveByTagName(Element base, String targetName){
    
    Element curEle = base;
    Element parent;
    while((parent = curEle.getParentElement()) != null){
      if(parent.getTagName().equalsIgnoreCase(targetName)){
        return parent;
      }
      curEle = parent;
    }
    return null;
  }
  
  
  
}


  