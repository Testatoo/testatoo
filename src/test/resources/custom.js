/*
 * Copyright (C) 2013 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
(function (w) {

  var cartridge = {
    name: 'custom_cartridge',
    components: []
  };

  var $ = w.testatoo;

  $.registerCartridge(cartridge);

  cartridge.components.push({
    type: 'CustomComponent',
    match: function(el) { return el.attr('data-role') == 'custom-comp' },
    visible: function(el) {
      return el.is(':visible');
    }
  });

  cartridge.components.push({
    type: 'CustomTag',
    match: function(el) { return el.is('CUSTOM-TAG') },
    visible: function(el) {
      return el.is(':visible');
    }
  });

}(window));
