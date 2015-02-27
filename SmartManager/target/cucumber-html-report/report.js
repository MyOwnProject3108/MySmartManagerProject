$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("test/java/EndToEndMerchandising.feature");
formatter.feature({
  "id": "end-to-end-tests-for-merchandising",
  "description": "",
  "name": "End to End Tests for Merchandising",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 5218874400,
  "status": "passed"
});
formatter.background({
  "description": "",
  "name": "Pre-requisite",
  "keyword": "Background",
  "line": 3,
  "type": "background"
});
formatter.step({
  "name": "I login as user \"Zach\"",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "I should be on \"Peerius Smart Manager\" page",
  "keyword": "Then ",
  "line": 5
});
formatter.step({
  "name": "I search for site \"demostoredev\"",
  "keyword": "And ",
  "line": 6
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "Peerius Smart Manager",
      "offset": 16
    }
  ],
  "location": "StepDefinitions.I_should_be_on_page(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "demostoredev",
      "offset": 19
    }
  ],
  "location": "StepDefinitions.I_search_for_site(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "id": "end-to-end-tests-for-merchandising;end-to-end-test-with-simple-rule-and-master-rule",
  "tags": [
    {
      "name": "@E2Emaster",
      "line": 8
    }
  ],
  "description": "",
  "name": "End to End Test With Simple Rule And Master Rule",
  "keyword": "Scenario",
  "line": 9,
  "type": "scenario"
});
formatter.step({
  "name": "I click on \"Create a New Campaign\" option in \"Merchandising\"",
  "keyword": "When ",
  "line": 10
});
formatter.step({
  "name": "I Should be on Merchandising \"Create a New Campaign\" page",
  "keyword": "Then ",
  "line": 11
});
formatter.step({
  "name": "I create Simple Campaign with name \"E2EAutoCreate\"",
  "keyword": "And ",
  "line": 12
});
formatter.step({
  "name": "I click on Link \"2.  Master Rules\"",
  "keyword": "Then ",
  "line": 13
});
formatter.step({
  "name": "I click on button \"Toggle Advanced\"",
  "keyword": "And ",
  "line": 14
});
formatter.step({
  "name": "I Set Master Rule \"(r.saleprice\u003c20)\"",
  "keyword": "Then ",
  "line": 15
});
formatter.step({
  "name": "I click on button \"Save Campaign\"",
  "keyword": "And ",
  "line": 16
});
formatter.step({
  "name": "I Should See Campaign \"E2EAutoCreate\" on Overview Page",
  "keyword": "Then ",
  "line": 17
});
formatter.match({
  "arguments": [
    {
      "val": "Create a New Campaign",
      "offset": 12
    },
    {
      "val": "Merchandising",
      "offset": 46
    }
  ],
  "location": "StepDefinitions.I_click_on_option_in(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "Toggle Advanced",
      "offset": 19
    }
  ],
  "location": "StepDefinitions.i_click_on_button(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "(r.saleprice\u003c20)",
      "offset": 19
    }
  ],
  "location": "StepDefinitions.i_Set_Master_Rule(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Save Campaign",
      "offset": 19
    }
  ],
  "location": "StepDefinitions.i_click_on_button(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "E2EAutoCreate",
      "offset": 23
    }
  ],
  "location": "StepDefinitions.I_Should_See_Campaign_on_Overview_Page(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 139155324,
  "status": "passed"
});
});