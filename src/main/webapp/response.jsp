<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <link rel="icon" href="../../../../favicon.ico" />

    <title>Jumbotron Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron.css" rel="stylesheet" />
  </head>

  <body style="padding-top: 0px;">
    <main role="main">
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <div class="container">
          <h1 class="display-3">WebApp v1</h1>
          <p>
            <span class="badge badge-success">${containerid}</span><br>
            <span class="badge badge-success">${containerip}</span>
          </p>
          <p>
            Cloud Academy is an enterprise-ready training platform that accelerates teams and digital transformation.
            Leverage our multi-cloud training library to move faster.
            Skills are built when knowledge, experience, and context intersect. Our vendor neutral training catalog is developed and maintained by experts on AWS, Azure, GCP, CI/CD, containers, security, IoT, data science, machine learning, big data, and beyond.

          </p>
          <p>
            <a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a>
          </p>
        </div>
      </div>

      <div class="container">
        <!-- Example row of columns -->
        <div class="row">
          <div class="col-md-4">
            <h2>Action 1</h2>
            <p>
              Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
              tellus ac cursus commodo, tortor mauris condimentum nibh, ut
              fermentum massa justo sit amet risus. Etiam porta sem malesuada
              magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
              <button id="action1" type="button" class="btn btn-outline-success">
                Action 1
              </button>
            </p>
          </div>
          <div class="col-md-4">
            <h2>Action 2</h2>
            <p>
              Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
              tellus ac cursus commodo, tortor mauris condimentum nibh, ut
              fermentum massa justo sit amet risus. Etiam porta sem malesuada
              magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
              <button id="action2" type="button" class="btn btn-outline-success">
                Action 2
              </button>
            </p>
          </div>
          <div class="col-md-4">
            <h2>Action 3</h2>
            <p>
              Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
              egestas eget quam. Vestibulum id ligula porta felis euismod
              semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris
              condimentum nibh, ut fermentum massa justo sit amet risus.
            </p>
            <p>
              <button id="action3" type="button" class="btn btn-outline-success">
                Action 3
              </button>
            </p>
          </div>
        </div>

        <hr />
      </div>
      <!-- /container -->
    </main>

    <footer class="container"><p>&copy; CloudAcademy ❤ DevOps 2018</p></footer>

    <!--
      Bootstrap core JavaScript
      ==================================================
    -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script>
      window.jQuery ||
        document.write('<script src="./js/jquery-3.3.1.min.js"><\/script>');
    </script>
    <script src="./js/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>

    <script type="text/javascript">
      $("#action1").on("click", function(event) {
        performAction("./action1");
      });
      $("#action2").on("click", function(event) {
        performAction("./action2");
      });
      $("#action3").on("click", function(event) {
        performAction("./action3");
      });

      function performAction(action) {
        $.ajax({
          url: action,
          type: "GET",
          success: function(data) {
            if (data == "success") {
              alert("request sent!");
            }
          }
        });
      }
    </script>
  </body>
</html>
