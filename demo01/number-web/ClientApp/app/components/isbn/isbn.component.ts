import { Component, OnInit } from '@angular/core';
import { IsbnresourceApi } from "../../isbn/index";

@Component({
    selector: 'app-isbn',
    templateUrl: './isbn.component.html',
    styleUrls: ['./isbn.component.css']
})
/** isbn component*/
export class IsbnComponent implements OnInit {


    isbn: string = "";

    /** isbn ctor */
    constructor(private api: IsbnresourceApi) {
    }

    /** Called by Angular after isbn component initialized */
    ngOnInit(): void {
        this.api.generateIsbnNumberUsingGETWithHttpInfo()
            .subscribe(r => {
                if (r.status === 200)
                    this.isbn = r.text();
            });

    }
}