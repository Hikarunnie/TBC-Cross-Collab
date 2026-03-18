**Demo Readiness Description:**

სისტემატიური ტესტირებისას უმეტესობა user-flowმ, ფუნქციონალმა წარმატებით გაიარა smoke და regression ტესტები. მაგალითად:

* Publishing new event – **pass**
* Registering for an event – **pass**
* Registering for a full event (waitlisted) – **pass**
* Deleting multiple events (Admin) – **pass**
* Canceling event from notifications – **pass**
* Canceling while editing event details (Admin) – **pass**
* Canceling while publishing new event – **pass**
* Clearing selection of multiple events (Admin) – **pass**
* Registration exports (Admin) – **pass**

Demo-სთვის ყველაზე უსაფრთხოდ ჩავთვალე ორი კრიტიკულად აუცილებელი user-flow: **Publishing new event** ადმინის მიერ და **Registering for an event**. ამიტომაც ეს ორი სცენარი, იმის საფუძველზე, რომ ყველა ტესტს წარმატებით გადიოდნენ მანუალურადაც და ავტომატიზირებულადაც, ჩაიდო CI/CD pipelineში და მზად იყო Demo-ზე წარსადგენად.

Smoke testing მიზნად ისახავდა სისტემის გაშვებადობის შემოწმებას, ძირითადი e2e user flow-ების (event creation, registration, waitlist) მუშაობის დადასტურებას და blocker ან critical severity ბაგების არარსებობის გადამოწმებას. ყოველი ახალი ვერსიის ჩაშვებისას ჯერ ამ რაღაცებს ვამოწმებდი თავიდან, ჩემს ავტომატიზირებულ ტესტებს ვუშვებდი და საბოლოოდ, ისეთი კრიტიკული ბაგი არ გამოვლენილა, გაგრძელების საშუალება რომ არ მოეცა. თუმცა დარჩა რამდენიმე ბაგიანი ფუნქციონალი (მაგალითად,editing event), რომელთაგან ყველა აღრიცხულია ჯირაზე.

Smoke ტესტირების შედეგადაც დადასტურდა რეგისტრაციის და ევენთის შექმნის flow-ების უსაფრთხოება და გაგრძელების საშუალება. შესაბამისად, მიმდინარე რისკს შევაფასებდი როგორც **Low**-ს.

სისტემა მზად არის დემოსთვის.
ყველა კრიტიკული user flow ფუნქციონირებს, ცნობილი შეზღუდვები არის მცირე UX დონეზე და წინასწარ კომუნიცირებადია.
