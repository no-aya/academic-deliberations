/*!

=========================================================
* Deliberation Dashboard Angular - v1.5.0
=========================================================

* Product Page: https://www.enset-media.ac.ma/product/deliberation-dashboard
* Copyright 2023 no.aya (https://www.enset-media.ac.ma)
* Licensed under MIT (https://github.com/no.aya/deliberation-dashboard/blob/master/LICENSE.md)

* Coded by no.aya

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
