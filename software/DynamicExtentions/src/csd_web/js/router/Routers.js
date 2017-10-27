var Routers = {
  formEventsRouterPointer : null,
  updateCachedFormMethod : null,

  /*
   * AdvancedPropertiesRouter. Used to perform actions initiated a Control's
   * User Interface
   */
  AdvancedPropertiesRouter : Backbone.Router.extend({
    routes : {
      "calculatedAttribute/:id/:operation" : "setCalculatedAttrib",
      "skipRules/:id/:operation" : "setSkipRule"
    },

    setCalculatedAttrib : function(id, operation) {
      // just remove for now
      if (operation == "delete") {
        GlobalMemory.formulaCounter = 0;
        $('#dialogMessageText').html('Do you wish to delete this formula?');
        $('#tempData').val(id);
        $("#general-dialog").dialog({
          buttons : {
            Yes : function() {
              AdvancedControlPropertiesBizLogic.deleteFormula($('#tempData').val());
              Main.advancedControlsView.setTableCss('formulaTable');
              $(this).dialog("close");
            },
            No : function() {
              $(this).dialog("close");
            }
          }
        });

        $("#general-dialog").dialog("open");
        Routers.formEventsRouterPointer.navigate('clear', {trigger : true});
      } else if (operation == "edit") {
        var _formula = $('#formulaTable')[0].rows[id].cells[2].innerHTML;
        // AdvancedControlPropertiesBizLogic.deleteFormula(id);
        $("#availableFields1").val(id);
        $('#formulaField').val(_formula);
        Routers.formEventsRouterPointer.navigate('clear', {trigger : true});
      }
    },

    setSkipRule : function(id, operation) {
      // just remove for now
      Main.advancedControlsView.clearMessage();
      if (operation == "delete") {
        GlobalMemory.formulaCounter = 0;
        $('#dialogMessageText').html('Do you wish to delete this skip rule?');
        $('#tempData').val(id);
        $("#general-dialog").dialog({
          buttons : {
            Yes : function() {
              AdvancedControlPropertiesBizLogic.deleteSkipRule($('#tempData').val());
              Main.advancedControlsView.setTableCss('skipRulesTable');
              $(this).dialog("close");
            },
            No : function() {
              $(this).dialog("close");
            }
          }
        });
        $("#general-dialog").dialog("open");
        Routers.formEventsRouterPointer.navigate('clear', {trigger : true});
      } else if (operation == "edit") {
        var skipRule = Main.formView.getFormModel().get('skipRules')[id];

        // AdvancedControlPropertiesBizLogic.deleteSkipRule(id);
        // Main.advancedControlsView.setTableCss('skipRulesTable');
        // set controlling fields
        $("#controllingField").val(skipRule.controllingAttributes);
        // set controlled fields
        $('#controlledField').val(skipRule.controlledAttributes);
        $("#controlledField").trigger("liszt:updated");

        // set action
        $('#' + skipRule.action).prop('checked', true).button('refresh');

        // set allOrAny
        $('#' + skipRule.allAny).prop('checked', true).button('refresh');
        // enable pvSubSet menu
        if (skipRule.action == "pvSubSet") {
          Main.advancedControlsView.populatePvSubSetDropDown();
          $('#pvSubSetDiv').show();
          $("#subsetPvs").chosen();
          $('#subsetPvs').val(skipRule.pvSubSet);
          $('#defaultPv').val(skipRule.defaultPv);
          if (skipRule.defaultPv == undefined
            || skipRule.defaultPv == ""
            || skipRule.defaultPv == " ") {
            $('#defaultPv').val("SELECT_VAL");
          }
          $("#subsetPvs").trigger("liszt:updated");
        } else {
          $('#pvSubSetDiv').hide();
        }

        AdvancedControlPropertiesBizLogic.setSkipRuleControllingFieldsUI();

        if (skipRule.controllingPvs != null && skipRule.controllingPvs != undefined) {
          $("#controllingValuesDiv").hide();
          $('#pvs').val(skipRule.controllingPvs);
          $("#pvs").trigger("liszt:updated");
          $("#pvDiv").show();

        } else if (skipRule.controllingValues != null
          && skipRule.controllingValues != undefined
          && skipRule.controllingCondition != null
          && skipRule.controllingCondition != undefined) {

          $("#pvDiv").hide();
          $('#controllingValuesCondition').val(skipRule.controllingCondition);
          $('#controllingValues').val(skipRule.controllingValues);
          $("#controllingValuesDiv").show();
        }
        Routers.formEventsRouterPointer.navigate('clear', {trigger : true});
        // set the skip rule id which is being edited
        GlobalMemory.skipRuleId = id;
      }
    }
  }),

  /*
   * Used to create control Node in the tree
   */

  /*
   * Form Router. Used to perform actions initiated a Container's User
   * Interface
   */

  FormRouter : Backbone.Router.extend({
    routes : {
      "loadCachedForm/:id/:edit" : "loadForm",
      "clear" : "dummy"
    },
    initialize : function() {
      // Routers.updateCachedFormMethod = this.updateFormAndUI;
      Routers.updateCachedFormMethod = this.updateFormAndUI;
    },

    dummy : function() {
    },

    loadForm : function(_id, edit) {
      $("#formWaitingImage").show();
      if (Main.formView == null) {
        Main.formView = Views.showForm('formTab',
          new Models.Form({
            "id" : _id
          })
        );
      }
      Main.formView.getFormModel().set({id : _id});
      GlobalMemory.editForm = (edit == "true");
      Main.formView.getFormModel().fetch({
        url : '../../rest/ng/de-forms/' + _id + "/" + edit,
        success : this.loadFormSuccessHandler
      });
    },

    updateId : function(nId) {
      Main.formView.getFormModel().set({
        id : nId,
        status : saved
      });
    },

    updateFormUI : function(model) {
      Main.formView.render();
    },

    updateTreeWithFormName : function(model) {
      Main.treeView.getTree().setItemText(1, $("<span/>").text(model.get('caption')).html(), 'Form\'s caption');
      Main.treeView.getTree().deleteChildItems(1);
    },

    updateFormAndUI : function(model) {
      /*
       * Routers.formEventsRouterPointer.updateFormUI(model);
       * Routers.formEventsRouterPointer
       * .updateTreeWithFormName(model);
       * Routers.formEventsRouterPointer
       * .loadControlsInModelAndTree(model); // re populate skip
       * rules table; $("#skipRulesTable tr:gt(0)").remove();
       * AdvancedControlPropertiesBizLogic.loadSkipRules(model);
       */
      $("#skipRulesTable tr:gt(0)").remove();
      Routers.formEventsRouterPointer.updateModelWithIds(model);
      AdvancedControlPropertiesBizLogic.loadSkipRules(model);
    },

    updateUI : function(model) {
      Routers.formEventsRouterPointer.updateFormUI(model);
      Routers.formEventsRouterPointer.updateTreeWithFormName(model);
      Routers.formEventsRouterPointer.loadControlsInModelAndTree(model);
      // re populate skip rules table;
      // $("#skipRulesTable tr:gt(0)").remove();
      AdvancedControlPropertiesBizLogic.loadSkipRules(model);
      // Routers.formEventsRouterPointer.updateModelWithIds(model);
    },

    loadFormulae : function(model, subFormName, subFormUDN) {
      for ( var key in model.get('controlObjectCollection')) {
        var control = model.get('controlObjectCollection')[key];
        if (control.get('type') == "numericField") {
          if (control.get('isCalculated') != undefined && control.get('isCalculated')) {
            var controlName = control.get('controlName');
            var userDefinedName = control.get('userDefinedName');
            if (subFormName != "") {
              controlName = subFormName + "." + controlName;
              userDefinedName = subFormUDN + "." + userDefinedName;
            }

            if (Main.advancedControlsView == null) {
              Main.advancedControlsView = new Views.AdvancedPropertiesTabView({
                el : $('#advancedControlProperties'),
                model : null
              });
            }
            Main.advancedControlsView.addFormulaToTable(controlName, control.get('formula'),userDefinedName);
            AdvancedControlPropertiesBizLogic.addFormulaToMemory(controlName,control.get('formula'));
          }
        }

        if (control.get('type') == "subForm") {
          this.loadFormulae(
            control.get('subForm'), 
            control.get('controlName'), 
            control.get('userDefinedName'));
        }
      }
    },

    loadFormSuccessHandler : function(model, response) {
      var formId = model.get('id');
      if (formId != undefined && formId != null) {
        var scope = parent.angular.element(".container.os-de-form").scope();
        scope.form.caption = model.get('caption');
        scope.$apply();
        GlobalMemory.editForm = true;
      }

      Routers.formEventsRouterPointer.updateUI(model);
      Routers.formEventsRouterPointer.loadFormulae(Main.formView.getFormModel(), "", "");
      AdvancedControlPropertiesBizLogic.loadSkipRules(Main.formView.getFormModel());
      Main.formView.getFormModel().set({
        skipRules : model.get('skipRules'),
        id : model.get('id'),
        caption: model.get('caption'),
        formName: model.get('formName')
      });
      Main.advancedControlsView.setTableCss('formulaTable');
      // Main.mainTabBarView.loadFormSummary();
      Main.mainTabBarView.getFormSummaryView().displayFormInfo(model.getFormInformation());
      $("#formWaitingImage").hide();
      // save form
      if (!GlobalMemory.editForm) {
        $('#saveForm').prop("value", " Save As ");
      }
    },

    updateModelWithIds : function(model) {
      var formModel =  Main.formView.getFormModel();
      // set form's id
      formModel.set({id : model.get('id')});
      // set controls' ids
      for ( var cntr = 0; cntr < model.get('controlCollection').length; cntr++) {
        var control = new Models.Field(model.get('controlCollection')[cntr]);
        formModel.setControlId(control.get('controlName'), control.get('id'));
        // set sub form controls' ids
        if (control.get('type' == "subForm")) {
          // set sub form id
          formModel.setSubFormId(control.get('controlName'), control.get('subForm').get('id'));
          // set sub controls' ids
          var subFrom = new Models.Form(control.get('subForm'));
          for ( var subCntr = 0; cntr < subFrom.get('controlCollection').length; subCntr++) {
            var subControl = new Models.Field(subFrom.get('controlCollection')[subCntr]);
            var subControlName = control.get('controlName') + "." + subControl.get('controlName');
            Main.formView.getFormModel().setControlId(subControlName, subControl.get('id'));
          }
        }
      }
    },

    loadControlsInModelAndTree : function(model) {
      var formModel = Main.formView.getFormModel();
      for (var i = 0; i < model.get('controlCollection').length; i++) {
        var control = new Models.Field(model.get('controlCollection')[i]);
        this.populateModelWithControls(formModel, control, false);
      }

      var controls = [];
      $.each(formModel.get('controlRows'), function(key, ctrls) {
        controls = controls.concat(ctrls);
      });

      // Need to add code to populate sub forms
      for ( var cntr = 0; cntr < controls.length; cntr++) {
        var control = formModel.get('controlObjectCollection')[controls[cntr]];
        var controlNodeId = this.populateTreeWithControlNodes(1, control);
        control.set({
          editName : control.get('controlName'),
          formTreeNodeId : controlNodeId,
          isSubFormControl: false
        });

        if (control.get('type') == "subForm") {
          var subFrm = control.get('subForm');
          for ( var subCntr = 0; subCntr < subFrm.get('controlCollection').length; subCntr++) {
            var subControl = new Models.Field(subFrm.get('controlCollection')[subCntr]);
            this.populateModelWithControls(subFrm, subControl, true);
            var subFrmCntrlNodeId = this.populateTreeWithControlNodes(controlNodeId, subControl);
            subControl.set({
              editName : control.get('controlName') + "." + subControl.get('controlName'),
              formTreeNodeId : subFrmCntrlNodeId,
              isSubFormControl: true
            });
          }
        }
       }
    },

    populateModelWithControls : function(formModel, control, isSubForm) {
      var updatedControl = Utility.addFieldHandlerMap[control.get('type')](control, false, 'controlContainer');
      formModel.get('controlObjectCollection')[control.get('controlName')] = updatedControl;
      formModel.get('controlsOrder').push(control.get('controlName'));
      
      if (!isSubForm) {
        var controls = formModel.get('controlRows')[control.get("sequenceNumber")] || [];
        controls.push(control.get("controlName"));
        formModel.get('controlRows')[control.get("sequenceNumber")] = controls;
      }
    },

    populateTreeWithControlNodes : function(parentId, control) {
      var tree =  Main.treeView.getTree();
      var id = GlobalMemory.nodeCounter++;
      var label = $("<span/>").text(control.get('caption') + " (" + control.get('userDefinedName') + ")").html();
      tree.insertNewChild(parentId, id, label, 0, 0, 0, 0);
      tree.setUserData(id, "controlName", control.get('controlName'));
      tree.setUserData(id, "controlType", control.get('type'));
      return id;
    }
  }),

  /*
   * Router initialization
   */
  initializeRouters : function() {
    this.formEventsRouterPointer = new this.FormRouter;
    var advancedControlPropsRouter = new this.AdvancedPropertiesRouter;
    Backbone.history.start();
  },

  designModeOnBeforeDragEvent : function() {
    var gridObject = Main.designModeViewPointer.getGridObject();
    gridObject.attachEvent(
      "onBeforeDrag",
      function(sId, sInd) {
        gridObject.rowToDragElement = function(id) {
          var text = gridObject.cells(sId, sInd).getValue();
          if (text == "") {
            text = "Empty";
          }
          return text;
        }
        return true;
      }
    );
  },

  designModeOnDragEvent : function() {
    Main.designModeViewPointer
      .getGridObject()
      .attachEvent(
        "onDrag",
        function(sId, tId, sObj, tObj, sInd, tInd) {
          var gridObject = Main.designModeViewPointer.getGridObject();
          try {
            var sourceCell = gridObject.cells(sId, sInd);
            var targetCell = gridObject.cells(tId, tInd);

            var sourceColumnIndex = sInd;
            var sourceRowIndex = gridObject.getRowIndex(sId);

            var targetColumnIndex = tInd;
            var targetRowIndex = gridObject.getRowIndex(tId);

            var sourceValue = sourceCell.getValue();
            var targetValue = targetCell.getValue();

            var sourceName = sourceCell.getAttribute("name");
            var targetName = targetCell.getAttribute("name");

            // first use case : the source and target rows
            // have values, shift the cells present in the
            // target's column
            // second use case : the source has value but
            // target does not
            // third use case : shifting within the same
            // column
            // first use case : they both have values

            if (sourceValue != "" && targetValue != "") {
              if (targetColumnIndex == sourceColumnIndex) {
                // case 3

                // case 3.1 where source > target

                if (sourceRowIndex > targetRowIndex) {
                  var totalNumberOfRows = gridObject.getRowsNum();

                  targetCell.setValue(sourceValue);
                  targetCell.setAttribute("name", sourceName);

                  sourceCell.setValue("");
                  sourceCell.setAttribute("name", "");

                  DesignModeBizLogic.reArrangeRows(
                    targetRowIndex + 1,
                    sourceRowIndex,
                    targetValue, targetName,
                    targetColumnIndex, false);
                } else if (sourceRowIndex < targetRowIndex) {
                  // case 3.2

                  targetCell.setValue(sourceValue);
                  targetCell.setAttribute("name", sourceName);

                  sourceCell.setValue("");
                  sourceCell.setAttribute("name", "");

                  DesignModeBizLogic.reArrangeRows(
                    targetRowIndex - 1,
                    sourceRowIndex,
                    targetValue, targetName,
                    targetColumnIndex, true);
                }
              } else {
                // case 1
                // add a row and then shift all values
                // downwards
                gridObject.addRow(gridObject.uid(), "");
                targetCell.setValue(sourceValue);

                sourceCell.setValue("");
                // later
                var totalNumberOfRows = gridObject.getRowsNum();
                DesignModeBizLogic.reArrangeRows(
                  targetRowIndex + 1,
                  totalNumberOfRows - 1,
                  targetValue, targetName,
                  targetColumnIndex, false);
              }
            }
            // second use case : target does not have value,
            // source does
            if (sourceValue != "" && targetValue == "") {
              targetCell.setValue(sourceValue);
              targetCell.setAttribute("name", sourceName);

              sourceCell.setValue("");
              sourceCell.setAttribute("name", "");
            }
          } catch (e) {
            alert("Error moving control.");
          }
        });
  },

  populateDesignModeTab : function() {
    var formModel =  Main.formView.getFormModel();
    if (formModel && formModel.get('controlObjectCollection') != null) {
      // add rows
      for ( var i = 0; i < ControlBizLogic.getNextSequenceNumber() - 1; i++) {
        var newId = (new Date()).valueOf();
        Main.designModeViewPointer.getGridObject().addRow(newId + i, "");
      }

      for ( var index in formModel.get('controlObjectCollection')) {
        // -1 because db doesn't accept zero and grid zero based
        var xPos = formModel.get('controlObjectCollection')[index].get("xPos") - 1;
        // -1 because db doesn't accept zero and grid zero based
        var seqNumber = formModel.get('controlObjectCollection')[index].get("sequenceNumber") - 1;
        var name = formModel.get('controlObjectCollection')[index].get("controlName");
        var udn = formModel.get('controlObjectCollection')[index].get("userDefinedName");
        // alert("xpos= " + xPos + " seqNumber = " + seqNumber + " name=
        // " + name + " gridRows = " + layoutGrid.getRowsNum() + "
        // gridColNum = " +layoutGrid.getColumnsNum());

        // this will add columns if not present
        var gridObject = Main.designModeViewPointer.getGridObject();
        while (gridObject.getColumnsNum() - 1 < xPos) {
          var colNum = gridObject.getColumnsNum();
          gridObject.insertColumn(colNum, '', 'ro', '300', '', 'center');
        }
        gridObject.cellByIndex(seqNumber, xPos).setValue(udn);
        gridObject.cellByIndex(seqNumber, xPos).setAttribute("name", name);
      }
    }
  }
}
