# **Spring Boot Backend + Vue.js Frontend Template**

### With GitHub Actions, Docker Compose, Dockerfiles, JPA, and Example Data to Get You Started 🚀

Everything you need to kickstart your Spring Boot + Vue.js project for [the DAT 3rd-semester exam]. 🌟

**Created: November 2024**  
*⚠️ Check versions and update GitHub Actions or dependencies as needed.*

---

## **Installation**

1. Clone this repository:
   ```bash
   git clone https://github.com/empasquer/springboot-vue-template.git
   ```
2. You're ready to go!

---

## **Usage**

1. Ensure Docker/OrbStack is installed and running.
2. Ensure no other applications are running on the ports used (8080, 5173, 3307)
3. Start your application:
   ```bash
   docker-compose build --no-cache
   ```
      ```bash
   docker compose up -d
   ```
4. To stop the containers:
   ```bash
   docker compose down
   ```
5. Verify running containers:
   ```bash
   docker ps
   ```
   *For more Docker Compose commands, refer to the [official documentation](https://docs.docker.com/reference/cli/docker/compose/).*

6. Access your application:
    - **Frontend**: [http://localhost:5173](http://localhost:5173)  
      *(If you encounter CORS issues, use [http://127.0.0.1:5173](http://127.0.0.1:5173) instead.)*
    - **Backend**: [http://localhost:8080](http://localhost:8080)
    - **H2 Database**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

*⚠️ It may take the db some time to get initialized - breathe for 30 seconds and it should be alright then.*

🎉 Your app is now running. Great job!

---

## **Customizing the Project**

Here are a few steps to make this template your own:

- [ ] Rename the folder to match your project.
- [ ] Refactor the project name, application name, and metadata as needed. 
- [ ] GO TO `compose.yml` and change all environmental variables to your own (⚠️ messes with the test data)
- [ ] In that same file, make sure to comment and uncomment which profile you want to use (details in file)
- [ ] Review and modify the provided data examples in the `data/` directory.
- [ ] Check the `src/configs` folder:
    - `InitDataExample.java`: Examples for populating your database with JPA.
    - `WebConfig.java`: Customize if necessary, but keep a backup copy.

### **Backend: Spring Boot**
- JPA entity examples can be found in the `models/` folder.
- Check `InitDataExample.java` for guidance on working with JPA.

### **Frontend: Vue.js**
- Modify `App.vue` to reflect your data fetching needs using `fetch` or `axios`.

---

## **Optional: Add Tailwind CSS**

To enhance your frontend styling, you can install Tailwind CSS with the following steps:

1. Install the necessary dependencies:
   ```bash
   npm install -D tailwindcss@latest postcss@latest autoprefixer@latest
   ```
2. Initialize Tailwind:
   ```bash
   npx tailwindcss init -p
   ```
3. Configure and start using Tailwind! 🎨

For more customization tips, refer to the [Tailwind CSS documentation](https://tailwindcss.com/).

---

## **Helpful Links**

- **Vue.js Quick Start Guide**: [https://vuejs.org/guide/quick-start.html](https://vuejs.org/guide/quick-start.html)
- **Spring Boot Tutorial**: [https://docs.spring.io/spring-boot/tutorial/first-application/index.html](https://docs.spring.io/spring-boot/tutorial/first-application/index.html)
- **Tailwind CSS**: [https://tailwindcss.com/](https://tailwindcss.com/)

---

## **Final Notes**

This template provides a great starting point for your project. Customize it to suit your needs and start building something amazing! 🚀

Enjoy!  
— *Emma*