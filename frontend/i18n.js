// ==========================================
// KISANSETU - GLOBAL i18n MODULE
// ==========================================

const KisanSetuI18n = {
    STORAGE_KEY: 'kisansetu_language',

    translations: {
        en: {
            // Login
            loginTitle: "Login - KisanSetu", welcomeBack: "Welcome Back!",
            emailLabel: "Email Address", emailPlaceholder: "Enter your email",
            passwordLabel: "Password", passwordPlaceholder: "Enter your password",
            loginBtn: "Login", noAccount: "Don't have an account?", registerHere: "Register here",
            farmer: "Farmer", buyer: "Buyer", back: "Back", logout: "Logout",

            // Register
            registerTitle: "Register - KisanSetu", createAccount: "Create Account",
            joinSubtitle: "Join the smart farming revolution",
            fullNameLabel: "Full Name", fullNamePlaceholder: "Enter your full name",
            phoneLabel: "Phone Number", phonePlaceholder: "Phone number",
            emailPlaceholderReg: "Email address",
            orgLabel: "Organization Name", orgPlaceholder: "Your organization (optional)",
            passwordPlaceholderReg: "Create password (min 6 characters)",
            createAccountBtn: "Create Account", alreadyAccount: "Already have an account?", loginHere: "Login here",

            // Dashboard Common
            totalOrders: "Total Orders", pending: "Pending", pendingApproval: "Pending Approval",
            active: "Active", activeOrders: "Active Orders", completed: "Completed",
            myCrops: "My Crops", rating: "Rating", buyerRating: "Buyer Rating", farmerRating: "Farmer Rating",

            // Farmer Dashboard
            addNewCrop: "Add New Crop", cropName: "Crop Name", cropNamePlaceholder: "e.g., Organic Tomatoes",
            category: "Category", selectCategory: "Select Category",
            vegetable: "Vegetable", fruit: "Fruit", grain: "Grain", pulse: "Pulse", other: "Other",
            quantity: "Quantity (kg)", quantityPlaceholder: "e.g., 500",
            aiPriceSuggestion: "AI Price Suggestion", calculatePrice: "Calculate Price",
            enterCropDetails: "Enter crop details to get AI suggestion",
            yourPrice: "Your Price (₹/kg)", pricePlaceholder: "e.g., 35",
            imageUrl: "Image URL (optional)", imagePlaceholder: "https://example.com/image.jpg",
            location: "Location", locationPlaceholder: "e.g., Lucknow, UP",
            addCropListing: "Add Crop Listing",
            myCropsTitle: "My Crops", incomingOrderRequests: "Incoming Order Requests",
            totalOrdersTitle: "Total Orders", allOrders: "All Orders",
            confirmed: "Confirmed", cancelled: "Cancelled",
            acceptOrder: "Accept Order", decline: "Decline",
            markAsShipped: "Mark as Shipped", markAsDelivered: "Mark as Delivered",
            basedOnPerformance: "Based on order performance", successful: "Successful",

            // Buyer Dashboard
            availableCrops: "Available Crops", searchPlaceholder: "Search crops by name...",
            categoryLabel: "Category", allCategories: "All Categories",
            vegetables: "Vegetables", fruits: "Fruits", grains: "Grains", pulses: "Pulses",
            sortByPrice: "Sort by Price", defaultSort: "Default", lowToHigh: "Low to High", highToLow: "High to Low",
            farmerRatingLabel: "Farmer Rating", allRatings: "All Ratings",
            myOrders: "My Orders", viewAll: "View All",
            basedOnPurchases: "Based on completed purchases",
            placeOrder: "Place Order", crop: "Crop", availableQuantity: "Available Quantity",
            orderQuantity: "Order Quantity (kg)", orderQtyPlaceholder: "Enter quantity",
            totalAmount: "Total Amount", deliveryAddress: "Delivery Address",
            deliveryPlaceholder: "Enter delivery address",

            // Buyer Orders
            ordersTitle: "My Orders", shopNow: "Shop Now",

            // Dynamic JS content
            orderNow: "Order Now", kgAvailable: "kg available", notSpecified: "Not specified",
            unknown: "Unknown", cropsFound: "crops found", noMatchCrops: "No crops match your search criteria.",
            noOrdersYet: "No orders yet. Start shopping!", noCropsYet: "No crops listed yet. Add your first crop!",
            noPendingOrders: "No pending order requests. Waiting for buyers...",
            noOrdersWaiting: "No orders yet. Waiting for buyers...",
            cancelOrder: "Cancel Order", confirmDelivery: "Confirm Delivery",
            perKg: "/kg", items: "items", deleteBtn: "Delete", editBtn: "Edit",
            youWillReceive: "You will receive", youReceive: "You receive",
            placingOrder: "Placing Order...",
            statusPending: "🟡 PENDING", statusConfirmed: "🟢 CONFIRMED",
            statusRejected: "🔴 REJECTED", statusCancelled: "⚫ CANCELLED",
            statusOutOfStock: "⚠️ OUT OF STOCK", statusShipped: "🚚 SHIPPED",
            statusDelivered: "📦 DELIVERED", statusCompleted: "✅ COMPLETED",
            msgPending: "⏳ Waiting for farmer approval",
            msgConfirmed: "✅ Farmer accepted! Preparing for shipment",
            msgRejected: "❌ Farmer rejected. Try another crop",
            msgCancelled: "⛔ You cancelled this order",
            msgOutOfStock: "⚠️ Crop no longer available",
            msgShipped: "🚚 Order is on the way!",
            msgDelivered: "📦 Order delivered! Please confirm",
            msgCompleted: "✅ Order completed successfully!",
            escrowLocked: "💰 Escrow Locked", paymentSuccess: "✅ Payment Successful",
            paymentReleased: "✅ Payment Released", paymentRefunded: "🔄 Refunded",
        },
        hi: {
            loginTitle: "लॉगिन - किसान सेतु", welcomeBack: "वापस स्वागत है!",
            emailLabel: "ईमेल पता", emailPlaceholder: "अपना ईमेल दर्ज करें",
            passwordLabel: "पासवर्ड", passwordPlaceholder: "अपना पासवर्ड दर्ज करें",
            loginBtn: "लॉगिन", noAccount: "खाता नहीं है?", registerHere: "यहाँ रजिस्टर करें",
            farmer: "किसान", buyer: "खरीदार", back: "वापस", logout: "लॉगआउट",

            registerTitle: "रजिस्टर - किसान सेतु", createAccount: "खाता बनाएं",
            joinSubtitle: "स्मार्ट कृषि क्रांति में शामिल हों",
            fullNameLabel: "पूरा नाम", fullNamePlaceholder: "अपना पूरा नाम दर्ज करें",
            phoneLabel: "फोन नंबर", phonePlaceholder: "फोन नंबर",
            emailPlaceholderReg: "ईमेल पता",
            orgLabel: "संगठन का नाम", orgPlaceholder: "आपका संगठन (वैकल्पिक)",
            passwordPlaceholderReg: "पासवर्ड बनाएं (न्यूनतम 6 अक्षर)",
            createAccountBtn: "खाता बनाएं", alreadyAccount: "पहले से खाता है?", loginHere: "यहाँ लॉगिन करें",

            totalOrders: "कुल ऑर्डर", pending: "लंबित", pendingApproval: "स्वीकृति लंबित",
            active: "सक्रिय", activeOrders: "सक्रिय ऑर्डर", completed: "पूर्ण",
            myCrops: "मेरी फसलें", rating: "रेटिंग", buyerRating: "खरीदार रेटिंग", farmerRating: "किसान रेटिंग",

            addNewCrop: "नई फसल जोड़ें", cropName: "फसल का नाम", cropNamePlaceholder: "जैसे, जैविक टमाटर",
            category: "श्रेणी", selectCategory: "श्रेणी चुनें",
            vegetable: "सब्जी", fruit: "फल", grain: "अनाज", pulse: "दाल", other: "अन्य",
            quantity: "मात्रा (किग्रा)", quantityPlaceholder: "जैसे, 500",
            aiPriceSuggestion: "AI मूल्य सुझाव", calculatePrice: "मूल्य गणना",
            enterCropDetails: "ऐआई सुझाव प्राप्त करने के लिए फसल विवरण दर्ज करें",
            yourPrice: "आपकी कीमत (₹/किग्रा)", pricePlaceholder: "जैसे, 35",
            imageUrl: "छवि URL (वैकल्पिक)", imagePlaceholder: "https://example.com/image.jpg",
            location: "स्थान", locationPlaceholder: "जैसे, लखनऊ, UP",
            addCropListing: "फसल सूची जोड़ें",
            myCropsTitle: "मेरी फसलें", incomingOrderRequests: "आने वाले ऑर्डर अनुरोध",
            totalOrdersTitle: "कुल ऑर्डर", allOrders: "सभी ऑर्डर",
            confirmed: "पुष्ट", cancelled: "रद्द",
            acceptOrder: "ऑर्डर स्वीकारें", decline: "अस्वीकार",
            markAsShipped: "शिप किया गया", markAsDelivered: "डिलीवर किया गया",
            basedOnPerformance: "ऑर्डर प्रदर्शन पर आधारित", successful: "सफल",

            availableCrops: "उपलब्ध फसलें", searchPlaceholder: "फसल खोजें...",
            categoryLabel: "श्रेणी", allCategories: "सभी श्रेणियां",
            vegetables: "सब्जियां", fruits: "फल", grains: "अनाज", pulses: "दालें",
            sortByPrice: "मूल्य के अनुसार", defaultSort: "डिफ़ॉल्ट", lowToHigh: "कम से अधिक", highToLow: "अधिक से कम",
            farmerRatingLabel: "किसान रेटिंग", allRatings: "सभी रेटिंग",
            myOrders: "मेरे ऑर्डर", viewAll: "सभी देखें",
            basedOnPurchases: "पूर्ण खरीद पर आधारित",
            placeOrder: "ऑर्डर करें", crop: "फसल", availableQuantity: "उपलब्ध मात्रा",
            orderQuantity: "ऑर्डर मात्रा (किग्रा)", orderQtyPlaceholder: "मात्रा दर्ज करें",
            totalAmount: "कुल राशि", deliveryAddress: "डिलीवरी पता",
            deliveryPlaceholder: "डिलीवरी पता दर्ज करें",

            ordersTitle: "मेरे ऑर्डर", shopNow: "अभी खरीदें",

            orderNow: "अभी ऑर्डर करें", kgAvailable: "किग्रा उपलब्ध", notSpecified: "निर्दिष्ट नहीं",
            unknown: "अज्ञात", cropsFound: "फसलें मिलीं", noMatchCrops: "कोई फसल आपकी खोज से मेल नहीं खाती।",
            noOrdersYet: "अभी तक कोई ऑर्डर नहीं। खरीदारी शुरू करें!", noCropsYet: "अभी तक कोई फसल नहीं। अपनी पहली फसल जोड़ें!",
            noPendingOrders: "कोई लंबित ऑर्डर अनुरोध नहीं। खरीदारों की प्रतीक्षा...",
            noOrdersWaiting: "अभी तक कोई ऑर्डर नहीं। खरीदारों की प्रतीक्षा...",
            cancelOrder: "ऑर्डर रद्द करें", confirmDelivery: "डिलीवरी की पुष्टि करें",
            perKg: "/किग्रा", items: "आइटम", deleteBtn: "हटाएं", editBtn: "संपादित करें",
            youWillReceive: "आपको मिलेगा", youReceive: "आपको मिलता है",
            placingOrder: "ऑर्डर हो रहा है...",
            statusPending: "🟡 लंबित", statusConfirmed: "🟢 पुष्ट",
            statusRejected: "🔴 अस्वीकृत", statusCancelled: "⚫ रद्द",
            statusOutOfStock: "⚠️ स्टॉक में नहीं", statusShipped: "🚚 भेज दिया",
            statusDelivered: "📦 डिलीवर", statusCompleted: "✅ पूर्ण",
            msgPending: "⏳ किसान की स्वीकृति की प्रतीक्षा",
            msgConfirmed: "✅ किसान ने स्वीकार किया! शिपमेंट की तैयारी",
            msgRejected: "❌ किसान ने अस्वीकार किया। दूसरी फसल आज़माएं",
            msgCancelled: "⛔ आपने यह ऑर्डर रद्द किया",
            msgOutOfStock: "⚠️ फसल अब उपलब्ध नहीं",
            msgShipped: "🚚 ऑर्डर रास्ते में है!",
            msgDelivered: "📦 ऑर्डर डिलीवर! कृपया पुष्टि करें",
            msgCompleted: "✅ ऑर्डर सफलतापूर्वक पूर्ण!",
            escrowLocked: "💰 एस्क्रो में", paymentSuccess: "✅ भुगतान सफल",
            paymentReleased: "✅ भुगतान जारी", paymentRefunded: "🔄 वापसी",
        },
        kn: {
            loginTitle: "ಲಾಗಿನ್ - ಕಿಸಾನ್ ಸೇತು", welcomeBack: "ಮರಳಿ ಸ್ವಾಗತ!",
            emailLabel: "ಇಮೇಲ್ ವಿಳಾಸ", emailPlaceholder: "ನಿಮ್ಮ ಇಮೇಲ್ ನಮೂದಿಸಿ",
            passwordLabel: "ಪಾಸ್‌ವರ್ಡ್", passwordPlaceholder: "ನಿಮ್ಮ ಪಾಸ್‌ವರ್ಡ್ ನಮೂದಿಸಿ",
            loginBtn: "ಲಾಗಿನ್", noAccount: "ಖಾತೆ ಇಲ್ಲವೇ?", registerHere: "ಇಲ್ಲಿ ನೋಂದಣಿ ಮಾಡಿ",
            farmer: "ರೈತ", buyer: "ಖರೀದಿದಾರ", back: "ಹಿಂದೆ", logout: "ಲಾಗ್‌ಔಟ್",

            registerTitle: "ನೋಂದಣಿ - ಕಿಸಾನ್ ಸೇತು", createAccount: "ಖಾತೆ ರಚಿಸಿ",
            joinSubtitle: "ಸ್ಮಾರ್ಟ್ ಕೃಷಿ ಕ್ರಾಂತಿಗೆ ಸೇರಿ",
            fullNameLabel: "ಪೂರ್ಣ ಹೆಸರು", fullNamePlaceholder: "ನಿಮ್ಮ ಪೂರ್ಣ ಹೆಸರನ್ನು ನಮೂದಿಸಿ",
            phoneLabel: "ಫೋನ್ ಸಂಖ್ಯೆ", phonePlaceholder: "ಫೋನ್ ಸಂಖ್ಯೆ",
            emailPlaceholderReg: "ಇಮೇಲ್ ವಿಳಾಸ",
            orgLabel: "ಸಂಸ್ಥೆಯ ಹೆಸರು", orgPlaceholder: "ನಿಮ್ಮ ಸಂಸ್ಥೆ (ಐಚ್ಛಿಕ)",
            passwordPlaceholderReg: "ಪಾಸ್‌ವರ್ಡ್ ರಚಿಸಿ (ಕನಿಷ್ಠ 6 ಅಕ್ಷರಗಳು)",
            createAccountBtn: "ಖಾತೆ ರಚಿಸಿ", alreadyAccount: "ಈಗಾಗಲೇ ಖಾತೆ ಇದೆಯೇ?", loginHere: "ಇಲ್ಲಿ ಲಾಗಿನ್ ಮಾಡಿ",

            totalOrders: "ಒಟ್ಟು ಆರ್ಡರ್‌ಗಳು", pending: "ಬಾಕಿ", pendingApproval: "ಅನುಮೋದನೆ ಬಾಕಿ",
            active: "ಸಕ್ರಿಯ", activeOrders: "ಸಕ್ರಿಯ ಆರ್ಡರ್‌ಗಳು", completed: "ಪೂರ್ಣ",
            myCrops: "ನನ್ನ ಬೆಳೆಗಳು", rating: "ರೇಟಿಂಗ್", buyerRating: "ಖರೀದಿದಾರ ರೇಟಿಂಗ್", farmerRating: "ರೈತ ರೇಟಿಂಗ್",

            addNewCrop: "ಹೊಸ ಬೆಳೆ ಸೇರಿಸಿ", cropName: "ಬೆಳೆ ಹೆಸರು", cropNamePlaceholder: "ಉದಾ., ಸಾವಯವ ಟೊಮೇಟೊ",
            category: "ವರ್ಗ", selectCategory: "ವರ್ಗ ಆಯ್ಕೆಮಾಡಿ",
            vegetable: "ತರಕಾರಿ", fruit: "ಹಣ್ಣು", grain: "ಧಾನ್ಯ", pulse: "ಬೇಳೆ", other: "ಇತರ",
            quantity: "ಪ್ರಮಾಣ (ಕೆಜಿ)", quantityPlaceholder: "ಉದಾ., 500",
            aiPriceSuggestion: "AI ಬೆಲೆ ಸಲಹೆ", calculatePrice: "ಬೆಲೆ ಲೆಕ್ಕಹಾಕಿ",
            enterCropDetails: "AI ಸಲಹೆ ಪಡೆಯಲು ಬೆಳೆ ವಿವರಗಳನ್ನು ನಮೂದಿಸಿ",
            yourPrice: "ನಿಮ್ಮ ಬೆಲೆ (₹/ಕೆಜಿ)", pricePlaceholder: "ಉದಾ., 35",
            imageUrl: "ಚಿತ್ರ URL (ಐಚ್ಛಿಕ)", imagePlaceholder: "https://example.com/image.jpg",
            location: "ಸ್ಥಳ", locationPlaceholder: "ಉದಾ., ಬೆಂಗಳೂರು, KA",
            addCropListing: "ಬೆಳೆ ಪಟ್ಟಿ ಸೇರಿಸಿ",
            myCropsTitle: "ನನ್ನ ಬೆಳೆಗಳು", incomingOrderRequests: "ಬರುತ್ತಿರುವ ಆರ್ಡರ್ ವಿನಂತಿಗಳು",
            totalOrdersTitle: "ಒಟ್ಟು ಆರ್ಡರ್‌ಗಳು", allOrders: "ಎಲ್ಲಾ ಆರ್ಡರ್‌ಗಳು",
            confirmed: "ದೃಢೀಕರಿಸಲಾಗಿದೆ", cancelled: "ರದ್ದು",
            acceptOrder: "ಆರ್ಡರ್ ಸ್ವೀಕರಿಸಿ", decline: "ನಿರಾಕರಿಸಿ",
            markAsShipped: "ಶಿಪ್ ಮಾಡಲಾಗಿದೆ", markAsDelivered: "ಡೆಲಿವರ್ ಮಾಡಲಾಗಿದೆ",
            basedOnPerformance: "ಆರ್ಡರ್ ಕಾರ್ಯಕ್ಷಮತೆ ಆಧಾರಿತ", successful: "ಯಶಸ್ವಿ",

            availableCrops: "ಲಭ್ಯವಿರುವ ಬೆಳೆಗಳು", searchPlaceholder: "ಬೆಳೆಗಳನ್ನು ಹುಡುಕಿ...",
            categoryLabel: "ವರ್ಗ", allCategories: "ಎಲ್ಲಾ ವರ್ಗಗಳು",
            vegetables: "ತರಕಾರಿಗಳು", fruits: "ಹಣ್ಣುಗಳು", grains: "ಧಾನ್ಯಗಳು", pulses: "ಬೇಳೆಕಾಳುಗಳು",
            sortByPrice: "ಬೆಲೆ ಪ್ರಕಾರ", defaultSort: "ಡೀಫಾಲ್ಟ್", lowToHigh: "ಕಡಿಮೆಯಿಂದ ಹೆಚ್ಚು", highToLow: "ಹೆಚ್ಚಿನಿಂದ ಕಡಿಮೆ",
            farmerRatingLabel: "ರೈತ ರೇಟಿಂಗ್", allRatings: "ಎಲ್ಲಾ ರೇಟಿಂಗ್‌ಗಳು",
            myOrders: "ನನ್ನ ಆರ್ಡರ್‌ಗಳು", viewAll: "ಎಲ್ಲವನ್ನೂ ನೋಡಿ",
            basedOnPurchases: "ಪೂರ್ಣಗೊಂಡ ಖರೀದಿಗಳ ಆಧಾರಿತ",
            placeOrder: "ಆರ್ಡರ್ ಮಾಡಿ", crop: "ಬೆಳೆ", availableQuantity: "ಲಭ್ಯ ಪ್ರಮಾಣ",
            orderQuantity: "ಆರ್ಡರ್ ಪ್ರಮಾಣ (ಕೆಜಿ)", orderQtyPlaceholder: "ಪ್ರಮಾಣ ನಮೂದಿಸಿ",
            totalAmount: "ಒಟ್ಟು ಮೊತ್ತ", deliveryAddress: "ಡೆಲಿವರಿ ವಿಳಾಸ",
            deliveryPlaceholder: "ಡೆಲಿವರಿ ವಿಳಾಸ ನಮೂದಿಸಿ",

            ordersTitle: "ನನ್ನ ಆರ್ಡರ್‌ಗಳು", shopNow: "ಈಗ ಖರೀದಿಸಿ",

            orderNow: "ಈಗ ಆರ್ಡರ್ ಮಾಡಿ", kgAvailable: "ಕೆಜಿ ಲಭ್ಯ", notSpecified: "ನಿರ್ದಿಷ್ಟಪಡಿಸಿಲ್ಲ",
            unknown: "ಅಜ್ಞಾತ", cropsFound: "ಬೆಳೆಗಳು ಕಂಡುಬಂದಿವೆ", noMatchCrops: "ನಿಮ್ಮ ಹುಡುಕಾಟಕ್ಕೆ ಯಾವುದೇ ಬೆಳೆ ಹೊಂದಿಕೆಯಾಗಿಲ್ಲ.",
            noOrdersYet: "ಇನ್ನೂ ಆರ್ಡರ್ ಇಲ್ಲ. ಖರೀದಿ ಪ್ರಾರಂಭಿಸಿ!", noCropsYet: "ಇನ್ನೂ ಬೆಳೆ ಪಟ್ಟಿ ಮಾಡಿಲ್ಲ. ನಿಮ್ಮ ಮೊದಲ ಬೆಳೆ ಸೇರಿಸಿ!",
            noPendingOrders: "ಬಾಕಿ ಆರ್ಡರ್ ವಿನಂತಿಗಳಿಲ್ಲ. ಖರೀದಿದಾರರ ನಿರೀಕ್ಷೆಯಲ್ಲಿ...",
            noOrdersWaiting: "ಇನ್ನೂ ಆರ್ಡರ್ ಇಲ್ಲ. ಖರೀದಿದಾರರ ನಿರೀಕ್ಷೆಯಲ್ಲಿ...",
            cancelOrder: "ಆರ್ಡರ್ ರದ್ದುಮಾಡಿ", confirmDelivery: "ಡೆಲಿವರಿ ದೃಢೀಕರಿಸಿ",
            perKg: "/ಕೆಜಿ", items: "ಐಟಂಗಳು", deleteBtn: "ಅಳಿಸಿ", editBtn: "ಬದಲಾಯಿಸಿ",
            youWillReceive: "ನೀವು ಪಡೆಯುತ್ತೀರಿ", youReceive: "ನೀವು ಪಡೆಯುತ್ತೀರಿ",
            placingOrder: "ಆರ್ಡರ್ ಮಾಡಲಾಗುತ್ತಿದೆ...",
            statusPending: "🟡 ಬಾಕಿ", statusConfirmed: "🟢 ದೃಢೀಕೃತ",
            statusRejected: "🔴 ತಿರಸ್ಕೃತ", statusCancelled: "⚫ ರದ್ದು",
            statusOutOfStock: "⚠️ ಸ್ಟಾಕ್ ಇಲ್ಲ", statusShipped: "🚚 ಕಳುಹಿಸಲಾಗಿದೆ",
            statusDelivered: "📦 ಡೆಲಿವರ್", statusCompleted: "✅ ಪೂರ್ಣ",
            msgPending: "⏳ ರೈತರ ಅನುಮೋದನೆಗಾಗಿ ಕಾಯಲಾಗುತ್ತಿದೆ",
            msgConfirmed: "✅ ರೈತರು ಒಪ್ಪಿಕೊಂಡರು! ಶಿಪ್ಮೆಂಟ್‌ಗೆ ಸಿದ್ಧತೆ",
            msgRejected: "❌ ರೈತರು ತಿರಸ್ಕರಿಸಿದರು. ಇನ್ನೊಂದು ಬೆಳೆ ಪ್ರಯತ್ನಿಸಿ",
            msgCancelled: "⛔ ನೀವು ಈ ಆರ್ಡರ್ ರದ್ದುಮಾಡಿದ್ದೀರಿ",
            msgOutOfStock: "⚠️ ಬೆಳೆ ಇನ್ನು ಲಭ್ಯವಿಲ್ಲ",
            msgShipped: "🚚 ಆರ್ಡರ್ ಹಾದಿಯಲ್ಲಿದೆ!",
            msgDelivered: "📦 ಆರ್ಡರ್ ಡೆಲಿವರ್! ದಯವಿಟ್ಟು ದೃಢೀಕರಿಸಿ",
            msgCompleted: "✅ ಆರ್ಡರ್ ಯಶಸ್ವಿಯಾಗಿ ಪೂರ್ಣ!",
            escrowLocked: "💰 ಎಸ್ಕ್ರೋ ಲಾಕ್", paymentSuccess: "✅ ಪಾವತಿ ಯಶಸ್ವಿ",
            paymentReleased: "✅ ಪಾವತಿ ಬಿಡುಗಡೆ", paymentRefunded: "🔄 ಮರುಪಾವತಿ",
        }
    },

    getSavedLanguage() {
        return localStorage.getItem(this.STORAGE_KEY) || 'en';
    },

    saveLanguage(lang) {
        localStorage.setItem(this.STORAGE_KEY, lang);
    },

    // Translate a key - for use in JS rendering functions
    t(key) {
        const lang = this.getSavedLanguage();
        const t = this.translations[lang] || this.translations['en'];
        return t[key] || this.translations['en'][key] || key;
    },

    applyTranslations(lang) {
        const t = this.translations[lang];
        if (!t) return;

        document.documentElement.lang = lang;

        document.querySelectorAll('[data-i18n]').forEach(el => {
            const key = el.dataset.i18n;
            if (t[key]) el.textContent = t[key];
        });

        document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
            const key = el.dataset.i18nPlaceholder;
            if (t[key]) el.placeholder = t[key];
        });

        document.querySelectorAll('[data-i18n-title]').forEach(el => {
            const key = el.dataset.i18nTitle;
            if (t[key]) document.title = t[key];
        });
    },

    init() {
        const lang = this.getSavedLanguage();
        this.applyTranslations(lang);
    }
};

// Shorthand
function _t(key) { return KisanSetuI18n.t(key); }

document.addEventListener('DOMContentLoaded', () => KisanSetuI18n.init());

