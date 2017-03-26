<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
</script>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

  <!-- Bootstrap 3.3.6 -->
  <!-- <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> -->
  <spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrapmincss" />
		<link href="${bootstrapmincss}" rel="stylesheet" />

				<!-- Select2 -->
 <!-- <link rel="stylesheet" href="../../plugins/select2/select2.min.css">-->
  <spring:url value="/resources/plugins/select2/select2.min.css" var="select2mincss" />
		<link href="${select2mincss}" rel="stylesheet" />

  <!-- Font Awesome -->
  <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"> -->
   <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontawesomemincss" />
		<link href="${fontawesomemincss}" rel="stylesheet" />

  <!-- Ionicons -->
 <!--  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> -->

  <!-- Theme style -->
  <!-- <link rel="stylesheet" href="dist/css/AdminLTE.min.css"> -->
  <spring:url value="/resources/dist/css/AdminLTE.min.css" var="AdminLTEmincss" />
		<link href="${AdminLTEmincss}" rel="stylesheet" />

  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<!--   <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css"> -->
   <spring:url value="/resources/dist/css/skins/skin-blue.min.css" var="skinbluemincss" />
		<link href="${skinbluemincss}" rel="stylesheet" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!--   <script src="../../plugins/datatables/jquery.dataTables.min.js"></script>
  <script src="../../plugins/datatables/dataTables.bootstrap.min.js"></script> -->
    <!-- <link rel="stylesheet" href="../../plugins/datatables/dataTables.bootstrap.css"> -->

    <spring:url value="/resources/plugins/datatables/dataTables.bootstrap.css" var="dataTablesbootstrapcss" />
		<link href="${dataTablesbootstrapcss}" rel="stylesheet" />


</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span id ="mylogo" class="logo-lg" onclick="location.href='<%=request.getContextPath()%>/dashboard'"><b>HORIZON</b></span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <!-- User Image -->
                        <spring:url value="/resources/dist/img/user2-160x160.jpg" var="user2160160jpg" />
                        <img src="${user2160160jpg}" class="img-circle" alt="User Image">
                      </div>
                      <!-- Message title and timestamp -->
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <!-- The message -->
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks Menu -->
          <li class="dropdown tasks-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- Inner menu: contains the tasks -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <!-- Task title and progress text -->
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <!-- The progress bar -->
                      <div class="progress xs">
                        <!-- Change the css width attribute to simulate progress -->
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <spring:url value="/resources/dist/img/user2-160x160.jpg" var="user2160160jpg" />
              <img src="${user2160160jpg}" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
               <spring:url value="/resources/dist/img/user2-160x160.jpg" var="user2160160jpg" />
                <img src="${user2160160jpg}" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
        	<spring:url value="/resources/dist/img/user2-160x160.jpg" var="user2160160jpg" />
          <img src="${user2160160jpg}" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">HEADER</li>
        <!-- Optionally, you can add icons to the links -->
        <li id="slidebarMenuCompany" class="menuclass" onclick="location.href='<%=request.getContextPath()%>/company'"><a href="#"><i class="fa fa-link"></i> <span>Company</span></a></li>
        <li id="slidebarMenuProduct" class="menuclass" onclick="location.href='<%=request.getContextPath()%>/product'"><a href="#"><i class="fa fa-link"></i> <span>Product</span></a></li>
        <%-- <li id="slidebarMenuService" class="menuclass" onclick="location.href='<%=request.getContextPath()%>/service'"><a href="#"><i class="fa fa-link"></i> <span>Service</span></a></li>--%>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Service</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#" onclick="location.href='<%=request.getContextPath()%>/service'"><i class="fa fa-link"></i>Type</a></li>
            <li><a href="#" onclick="location.href='<%=request.getContextPath()%>/request'"><i class="fa fa-link"></i>Request</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->

<!--   ======================================================================================================================= -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       <!--  Data Tables -->
       <!--  <small>Product Details</small> -->
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
        <!-- <li><a href="#">Tables</a></li>
        <li class="active">Data tables</li> -->
      </ol>
    </section>


    <section class="content" id="productContent">

    	 <div class="row">
        <div class="col-xs-12">


<!-- TODO contents starts from here -->


    <!--  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->
  <!-- Modal -->

  <!-- ALL forms should go inside this -->
  <div class="modal fade" id="productFormeModal" data-backdrop="static" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="productFormModal-title"></h4>
        </div>

         <!--  <p>Some text in the modal.</p> -->
           <div class="box box-primary">
        <!--    TODO i commented -->
            <!-- <div class="box-header with-border">
              <h3 class="box-title">Quick Example</h3>
            </div> -->
            <!-- /.box-header -->

            <!-- create Product form start -->
            <form role="form" id="createProductForm" class="inputForm">
              <div class="box-body">

              <!-- holds validation error -->
              <div class="FormvalidationError">
              <!-- <span style="display:block;">one</span>-->
              </div>


              <div class="row">
            <div class="col-md-8">
              <div class="form-group">
                <label>Company Name</label>
                <select class="form-control select2" id="createProductCompanyListID" style="width: 100%;">
                 <!--  <option>Alabama</option>
                  <option>Alaska</option> -->
                </select>
              </div>
            </div>

          </div>

                <div class="form-group">
                  <label for="productName">Name</label>
                  <input type="text" class="form-control" id="createProductNameID" placeholder="Enter Product name">
                </div>
                 <div class="form-group">
                  <label for="productType">Type</label>
                  <input type="text" class="form-control" id="createProductTypeID" placeholder="Enter Product type">
                </div>
              </div>

              <!-- /.box-body -->

              <div class="box-footer">
                <button type="button" id="createProductSubmitBtn" class="btn btn-primary">Submit</button>
               <!--  <button type="button" class="btn btn-primary">Close</button> -->
              </div>
            </form>
             <!-- create Product form end  -->

              <!-- Edit Product form start -->
            <form role="form" id="editProductForm" class="inputForm">
              <div class="box-body">

              <!-- holds validation error -->
              <div class="FormvalidationError">
              <!-- <span style="display:block;">one</span>-->
              </div>


              <div class="row">
            <div class="col-md-8">
              <div class="form-group">
                <label>Company Name</label>
                <select class="form-control select2" id="editProductCompanyListID" disabled="disabled" style="width: 100%;">
                 <!--  <option>Alabama</option>
                  <option>Alaska</option> -->
                </select>
              </div>
            </div>

          </div>

                <div class="form-group">
                  <label for="productName">Name</label>
                  <input type="text" class="form-control" id="editProductNameID" placeholder="Enter Product name">
                </div>
                 <div class="form-group">
                  <label for="productType">Type</label>
                  <input type="text" class="form-control" id="editProductTypeID" placeholder="Enter Product type">
                </div>
              </div>

              <!-- /.box-body -->

              <div class="box-footer">
                <button type="button" id="editProductSubmitBtn" class="btn btn-primary">Submit</button>
               <!--  <button type="button" class="btn btn-primary">Close</button> -->
              </div>
            </form>
             <!-- create Product form end  -->
             <!-- edit product form end  -->


          </div>


        </div>
        <!-- TODO -->
       <!--  <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div> -->
      </div>

    </div>



          <!-- /.box -->

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Product Details</h3>
              <div class="box-footer clearfix no-border">
             <!--  <button style="margin-right: 5px;" type="button" class="btn btn-default pull-left" data-toggle="modal" data-target="#companyCreateModal"><i class="fa fa-plus"></i> Add item</button> -->
              <button style="margin-right: 5px;" type="button" id="productCreateBtn" class="btn btn-default pull-left, myCustomButton"><i class="fa fa-plus"></i> Add Product</button>
              <button style="margin-right: 5px; display: none" type="button" id="productEditBtn" class="btn btn-default pull-left, myCustomButton"><i class="fa fa-pencil"></i> Edit Product</button>
              <button style="display: none" type="button" class="btn btn-default pull-left, myCustomButton" id="productDeleteBtn"><i class="fa  fa-trash"></i> Delete Product</button>
            </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="productData" class="table table-bordered table-striped display">

                <thead>
                <tr>
                  <th>Product Id</th>
                  <th>Name</th>
                  <th>Type</th>
                  <th>Company Name</th>
                  <th>Creation Time</th>
                  <th>Last Updated Time</th>
                </tr>
                </thead>

                <tbody>

               <!--  <tr>
                  <td>Misc</td>
                  <td>NetFront 3.1</td>
                  <td>Embedded devices</td>
                  <td>-</td>
                  <td>C</td>
                </tr> -->

                </tbody>

                <tfoot>
                <tr>
                  <th>Product Id</th>
                  <th>Name</th>
                  <th>Type</th>
                   <th>Company Name</th>
                  <th>Creation Time</th>
                  <th>Last Updated Time</th>
                </tr>
                </tfoot>

              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          </div>
        </div>
        <!-- /.col -->

      <!-- /.row -->
    </section>
    <!-- /.content -->


  </div>

  <!-- /.content-wrapper -->

  <!--   ======================================================================================================================= -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript::;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript::;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                  <span class="label label-danger pull-right">70%</span>
                </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->


		<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<spring:url value="/resources/plugins/jQuery/jquery-2.2.3.min.js" var="jquery223minjs" />
<script src="${jquery223minjs}"></script>
<!-- Bootstrap 3.3.6 -->
<spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrapminjs" />
<script src="${bootstrapminjs}"></script>
<!-- AdminLTE App -->
<spring:url value="/resources/dist/js/app.min.js" var="appminjs" />
<script src="${appminjs}"></script>

<spring:url value="/resources/plugins/datatables/jquery.dataTables.min.js" var="jquerydataTablesminjs" />
<script src="${jquerydataTablesminjs}"></script>

<spring:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js" var="dataTablesbootstrapminjs" />
<script src="${dataTablesbootstrapminjs}"></script>

<spring:url value="/resources/plugins/select2/select2.full.min.js" var="select2fullminjs" />
<script src="${select2fullminjs}"></script>

<spring:url value="/resources/zmycustomjs/mycommon.js" var="mycommonjs" />
<script src="${mycommonjs}"></script>

<spring:url value="/resources/zmycustomjs/myproduct.js" var="myproductjs" />
<script src="${myproductjs}"></script>



<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->


</body>
</html>
