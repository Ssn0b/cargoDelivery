<%@ include file="header.jsp"%>

<body>
<div class="intro">
    <div class="container">
        <div class="intro__inner">
            <h2 class="intro__suptitle">Lots of possibilities</h2>
            <h1 class="intro__title">Welcome to EUGO</h1>
            <a class="btn" href="#">Learn more</a>
        </div>
    </div>
</div>

<section class="section">
    <div class="container">
        <div class="section__header">
            <h3 class="section__suptitle">We do what we love</h3>
            <h2 class="section__title">The story about us</h2>
            <div class="section__text">
                <p>We are a team of young and passionate enthusiasts,
                    we have set ourselves the task of creating a convenient delivery service for our customers throughout Europe.
                    The customer's sense of importance is number one for us.
                    We nurture a culture of delivery and at the same time modernize our service so that you want to come to us again.
                    Remember, everything starts from small!
                </p>
            </div>
        </div>



        <div class="about">
            <div class="about__item">
                <div class="about__img">
                    <img src="${pageContext.request.contextPath}/img/image1.jpg" alt="">
                </div>
                <div class="about__text">super service</div>
            </div>
            <div class="about__item">
                <div class="about__img">
                    <img src="${pageContext.request.contextPath}/img/image2.jpg" alt="">
                </div>
                <div class="about__text">super clients</div>
            </div>
            <div class="about__item">
                <div class="about__img">
                    <img src="${pageContext.request.contextPath}/img/image3.jpg" alt="">
                </div>
                <div class="about__text">super cities</div>
            </div>
        </div>
    </div>
</section>

<div class="statistic">
    <div class="container">

        <div class="stat">
            <div class="stat__item">
                <div class="stat__count">500</div>
                <div class="stat__text">That's how many orders on average
                    we deliver to our happy clients
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">100%</div>
                <div class="stat__text">So many orders are delivered on time and safely
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">20</div>
                <div class="stat__text">The number of cities in which we work
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">1</div>
                <div class="stat__text">The exact figure of the life given to you,
                    which you do not use to the full without our company.
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">5</div>
                <div class="stat__text">Approximately how many minutes it takes to make an order on our website
                </div>
            </div>
        </div>

    </div>
</div>

<section class="section">
    <div class="container">

        <div class="section__header">
            <h3 class="section__suptitle">We work with</h3>
            <h2 class="section__title">Amazing Services</h2>
        </div>

        <div class="services" >
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/photography.png" alt="">

                <div class="services__title">On time</div>
                <div class="services__text">We deliver everything always on time and on schedule, you can be sure.</div>
            </div>
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/webdesign.png" alt="">

                <div class="services__title">Many locations</div>
                <div class="services__text">We are expanding all over the world.</div>
            </div>
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/creativity.png" alt="">

                <div class="services__title">Comfort</div>
                <div class="services__text">Thanks to the convenient interface, you can easily order delivery.</div>
            </div>
        </div>

<%--
        <hr>
--%>

        <div class="services" ">
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/seo.png" alt="">

                <div class="services__title">Self improvement</div>
                <div class="services__text">Our managers are always working on problems and are happy to help.</div>
            </div>
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/css-html.png" alt="">

                <div class="services__title">Home feeling</div>
                <div class="services__text">In cooperation with our company, you will feel the warmth of home.</div>
            </div>
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/digital.png" alt="">

                <div class="services__title">Modernity</div>
                <div class="services__text">We use modern technology to improve the delivery of your orders.</div>
            </div>
        </div>

    </div><!-- /.container -->
</section>

</body>
</html>
