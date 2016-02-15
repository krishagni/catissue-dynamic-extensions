/**
 * @author SANJAY
 */

var Main = {
  treeView : null,
  formView : null,
  mainTabBarView : null,
  currentFieldView : null,
  designModeViewPointer : null,
  carousel : null,
  advancedControlsView : null,

  renderUI : function() {
    Templates.loadTemplateList();
    Utility.initializeFieldHandlerMap();
    Views.showBody();
    var form = new Models.Form();
    this.formView = Views.showForm('formDetailsDiv', form);
    this.treeView = new Views.TreeView({
      el : $('#controlsTreeDiv'),
      model : null
    });

    this.treeView.getTree().attachEvent("onClick", ControlBizLogic.formTreeNodeClickHandler);

    this.mainTabBarView = new Views.TabBarView({
      el : $('#csdOperationsContainer'),
      model : null
    });
    Views.showControlTab('control');
    this.carousel = $('#controlTypesSlider');
    this.carousel.tinycarousel();
    Main.advancedControlsView = new Views.AdvancedPropertiesTabView({
      el : $('#advancedControlProperties'),
      model : null
    });

    Routers.initializeRouters();
    // init design mode
//    Main.designModeViewPointer = new Views.DesignMode({
//      el : $("#design")
//    });
//    Routers.designModeOnBeforeDragEvent();
//    Routers.designModeOnDragEvent();

  }
}

Main.renderUI();
var url = window.top.location.href;
var fragIdx = url.indexOf("#");
if (fragIdx != -1) {
  Routers.formEventsRouterPointer.navigate(url.substring(fragIdx + 1), true);
}

